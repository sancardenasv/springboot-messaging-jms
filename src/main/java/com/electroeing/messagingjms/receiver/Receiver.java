package com.electroeing.messagingjms.receiver;

import com.electroeing.messagingjms.models.Message;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class Receiver {
    private final static Logger logger = LoggerFactory.getLogger(Receiver.class);

    @JmsListener(destination = "test.queue")
    public void receiveMessage(String message) {
        try {
            Message msg = new Gson().fromJson(message, Message.class);
            logger.info("Received Message: {}", msg);
        } catch (Exception e) {
            logger.error("Error receiving Message: {}", message, e);
        }

    }
}
