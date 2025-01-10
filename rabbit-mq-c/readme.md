## Spring와 C데몬의 RabbitMQ를 이용한 송수신

```
POST http://localhost:8080/api/send-command?deviceId=device123&command=RESTART
```

```
gcc -o command_daemon command_daemon.c -lrabbitmq -L/usr/local/lib -Wl,-rpath,/usr/local/lib
./command_daemon
```
