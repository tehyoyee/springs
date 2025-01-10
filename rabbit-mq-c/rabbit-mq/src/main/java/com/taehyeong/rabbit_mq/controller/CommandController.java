package com.taehyeong.rabbit_mq.controller;

import com.taehyeong.rabbit_mq.service.CommandSenderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CommandController {

    private final CommandSenderService commandSenderService;

    public CommandController(CommandSenderService commandSenderService) {
        this.commandSenderService = commandSenderService;
    }

    @PostMapping("/send-command")
    public ResponseEntity<String> sendCommand(@RequestParam String deviceId, @RequestParam String command) {
        commandSenderService.sendCommand(deviceId, command);
        return ResponseEntity.ok("Command sent to device " + deviceId + ": " + command);
    }
}