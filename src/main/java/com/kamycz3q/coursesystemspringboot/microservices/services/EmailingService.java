package com.kamycz3q.coursesystemspringboot.microservices.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EmailingService {
    public void sendMessage(String email, String message) {
        log.info("Sent message to " + email);
        log.info(message);
    }
}
