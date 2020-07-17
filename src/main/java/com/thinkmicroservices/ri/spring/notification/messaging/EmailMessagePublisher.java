
package com.thinkmicroservices.ri.spring.notification.messaging;

import org.springframework.cloud.stream.annotation.EnableBinding;

/**
 *
 * @author cwoodward
 */
@EnableBinding(EmailMessageSource.class)
public class EmailMessagePublisher {
    
}
