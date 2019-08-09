package com.electroeing.messagingjms;

import ch.qos.logback.classic.spi.LoggingEvent;
import ch.qos.logback.core.Appender;
import com.electroeing.messagingjms.sender.MessageSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatcher;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.slf4j.LoggerFactory;
import org.springframework.jms.core.JmsTemplate;

import static org.mockito.Mockito.argThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MessageSenderTests {
    static { System.setProperty("logback.configurationFile", "logback-spring.xml");}

    @InjectMocks
    MessageSender messageSenderTestClass;
    @Mock
    JmsTemplate jmsTemplateMock;

    @Test
    public void testSendMessageSuccess() {
        // Arrange
        ch.qos.logback.classic.Logger root = (ch.qos.logback.classic.Logger) LoggerFactory.getLogger(ch.qos.logback.classic.Logger.ROOT_LOGGER_NAME);
        final Appender mockAppender = mock(Appender.class);
        when(mockAppender.getName()).thenReturn("MOCK");
        root.addAppender(mockAppender);

        // Act
        messageSenderTestClass.sendMessage("Testing", "test.queue");

        // Assert
        verify(mockAppender).doAppend(argThat(new ArgumentMatcher() {
            @Override
            public boolean matches(final Object argument) {
                System.out.println(((LoggingEvent)argument).getTimeStamp());
                return ((LoggingEvent)argument).getFormattedMessage().contains("SENDING MESSAGE - Testing");
            }
        }));

    }
}
