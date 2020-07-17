
package com.thinkmicroservices.ri.spring.notification.messaging;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 *
 * @author cwoodward
 */
public interface SmsMessageSource {
    public static final String SMS_MESSAGE_CHANNEL="smsMessageChannel";
    @Output(SmsMessageSource.SMS_MESSAGE_CHANNEL)
    MessageChannel smsMessage();
}
