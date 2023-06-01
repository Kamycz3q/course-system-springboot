package com.kamycz3q.coursesystemspringboot.microservices.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SMSService {
    public void sendMessage(String number, String message) {
        log.info("Sent message to " + number);
        log.info(message);
    }
}
