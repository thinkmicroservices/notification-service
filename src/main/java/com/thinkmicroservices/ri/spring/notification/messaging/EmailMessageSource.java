 
package com.thinkmicroservices.ri.spring.notification.messaging;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 *
 * @author cwoodward
 */
public interface EmailMessageSource {

    public static final String EMAIL_MESSAGE_CHANNEL="emailMessageChannel";
    @Output(EmailMessageSource.EMAIL_MESSAGE_CHANNEL)
    MessageChannel emailMessage();
}
