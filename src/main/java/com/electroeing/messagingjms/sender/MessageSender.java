package com.electroeing.messagingjms.sender;

import com.electroeing.messagingjms.models.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.ObjectMessage;
import javax.jms.Session;

@Component
public class MessageSender {
    private final static Logger logger = LoggerFactory.getLogger(MessageSender.class);
    @Autowired
    JmsTemplate jmsTemplate;

    public void sendMessage(final String message, String queueName) {
        logger.warn("SENDING MESSAGE - {}", message);
        jmsTemplate.send(queueName, new MessageCreator() {
            @Override
            public javax.jms.Message createMessage(Session session) throws JMSException {
                ObjectMessage objectMessage = session.createObjectMessage(message);
                return objectMessage;
            }
        });
    }
}
