#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdarg.h>          // 가변 인자 함수 처리를 위한 헤더
#include <amqp.h>
#include <amqp_tcp_socket.h>
#include <rabbitmq-c/framing.h>  // 수정된 헤더 파일
#include <unistd.h>          // close 함수 사용을 위한 헤더

void die_on_amqp_error(amqp_rpc_reply_t x, char const *context) {
    if (x.reply_type == AMQP_RESPONSE_NORMAL) return;

    fprintf(stderr, "%s: ", context);
    switch (x.reply_type) {
        case AMQP_RESPONSE_NONE:
            fprintf(stderr, "missing RPC reply type!\n");
            break;
        case AMQP_RESPONSE_LIBRARY_EXCEPTION:
            fprintf(stderr, "%s\n", amqp_error_string2(x.library_error));
            break;
        case AMQP_RESPONSE_SERVER_EXCEPTION:
            switch (x.reply.id) {
                case AMQP_CONNECTION_CLOSE_METHOD: {
                    amqp_connection_close_t *m = (amqp_connection_close_t *)x.reply.decoded;
                    fprintf(stderr, "server connection error %uh, message: %.*s\n",
                            m->reply_code, (int)m->reply_text.len, (char *)m->reply_text.bytes);
                    break;
                }
                case AMQP_CHANNEL_CLOSE_METHOD: {
                    amqp_channel_close_t *m = (amqp_channel_close_t *)x.reply.decoded;
                    fprintf(stderr, "server channel error %uh, message: %.*s\n",
                            m->reply_code, (int)m->reply_text.len, (char *)m->reply_text.bytes);
                    break;
                }
                default:
                    fprintf(stderr, "unknown server error, method id 0x%08X\n", x.reply.id);
                    break;
            }
            break;
        default:
            fprintf(stderr, "unknown reply type: %d\n", x.reply_type);
            break;
    }
    exit(1);
}

void die(const char *fmt, ...) {
    va_list ap;
    va_start(ap, fmt);
    vfprintf(stderr, fmt, ap);
    va_end(ap);
    exit(1);
}

int main(int argc, char const *const *argv) {
    const char *hostname = "localhost";
    int port = 5672;
    const char *username = "guest";
    const char *password = "guest";
    const char *queue = "commandQueue";

    amqp_connection_state_t conn = amqp_new_connection();
    amqp_socket_t *socket = amqp_tcp_socket_new(conn);
    if (!socket) {
        die("Creating TCP socket failed\n");
    }

    if (amqp_socket_open(socket, hostname, port)) {
        die("Opening TCP socket failed\n");
    }

    // 로그인
    amqp_rpc_reply_t login_reply = amqp_login(conn, "/", 0, 131072, 0, AMQP_SASL_METHOD_PLAIN, username, password);
    die_on_amqp_error(login_reply, "Logging in");

    amqp_channel_open(conn, 1);
    die_on_amqp_error(amqp_get_rpc_reply(conn), "Opening channel");

    // 큐 선언
    amqp_queue_declare(conn, 1, amqp_cstring_bytes(queue), 0, 1, 0, 0, amqp_empty_table);
    die_on_amqp_error(amqp_get_rpc_reply(conn), "Declaring queue");

    // 큐로부터 메시지 소비 시작
    // no_ack 파라미터를 0으로 설정하여 수동 메시지 인정 활성화
    amqp_basic_consume(conn, 1, amqp_cstring_bytes(queue), amqp_empty_bytes, 0, 0, 0, amqp_empty_table);
    die_on_amqp_error(amqp_get_rpc_reply(conn), "Consuming");

    printf("Waiting for messages. To exit press CTRL+C\n");

    while (1) {
        amqp_rpc_reply_t res;
        amqp_envelope_t envelope;

        amqp_maybe_release_buffers(conn);

        res = amqp_consume_message(conn, &envelope, NULL, 0);

        if (res.reply_type == AMQP_RESPONSE_NORMAL) {
            char *message_body = malloc(envelope.message.body.len + 1);
            memcpy(message_body, envelope.message.body.bytes, envelope.message.body.len);
            message_body[envelope.message.body.len] = '\0';

            printf("Received command: %s\n", message_body);

            // deviceId와 command 분리
            char *deviceId = strtok(message_body, ":");
            char *command = strtok(NULL, ":");

            if (deviceId && command) {
                printf("Device ID: %s, Command: %s\n", deviceId, command);
                // 명령어 처리 로직 추가
                // 예: "device123:RESTART"
            } else {
                fprintf(stderr, "Invalid command format\n");
            }

            // 수동 메시지 인정
            amqp_basic_ack(conn, 1, envelope.delivery_tag, 0);

            free(message_body);
            amqp_destroy_envelope(&envelope);
        } else {
            // 비정상적인 응답 유형을 처리
            fprintf(stderr, "Unexpected response type: %d\n", res.reply_type);
            if (res.reply_type == AMQP_RESPONSE_LIBRARY_EXCEPTION) {
                fprintf(stderr, "Library error: %s\n", amqp_error_string2(res.library_error));
            } else if (res.reply_type == AMQP_RESPONSE_SERVER_EXCEPTION) {
                fprintf(stderr, "Server exception occurred.\n");
            }
            // 필요에 따라 루프를 계속할지 결정
            // 여기서는 루프를 계속하도록 설정
            // break; // 주석 처리하여 루프를 계속하도록 함
        }
    }

    // 연결 종료
    amqp_channel_close(conn, 1, AMQP_REPLY_SUCCESS);
    amqp_connection_close(conn, AMQP_REPLY_SUCCESS);
    amqp_destroy_connection(conn);

    return 0;
}

