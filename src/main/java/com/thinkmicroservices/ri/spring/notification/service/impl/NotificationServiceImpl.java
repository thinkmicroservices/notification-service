package com.thinkmicroservices.ri.spring.notification.service.impl;

import com.thinkmicroservices.ri.spring.notification.i18n.I18NResourceBundle;
import com.thinkmicroservices.ri.spring.notification.messaging.EmailMessageSource;
import com.thinkmicroservices.ri.spring.notification.messaging.SmsMessageSource;
import com.thinkmicroservices.ri.spring.notification.messaging.message.EmailMessage;
import com.thinkmicroservices.ri.spring.notification.messaging.message.MmsMessage;
import com.thinkmicroservices.ri.spring.notification.messaging.message.SmsMessage;
import com.thinkmicroservices.ri.spring.notification.service.EmailQueueException;
import com.thinkmicroservices.ri.spring.notification.service.NotificationService;
import com.thinkmicroservices.ri.spring.notification.service.SmsQueueException;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import java.util.List;
import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

/**
 *
 * @author cwoodward
 */
@Service
@Slf4j
public class NotificationServiceImpl implements NotificationService {

    private Logger logger = LoggerFactory.getLogger(NotificationServiceImpl.class);
    private static final String EMAIL_NO_ATTACHMENT_MESSAGE_QUEUE_TYPE = "EMAIL_NO_ATTACHMENT";
    private static final String EMAIL_WITH_ATTACHMENT_QUEUE_MESSAGE_TYPE = "EMAIL_WITH_ATTACHMENT";
    private static final String SMS_QUEUE_MESSAGE_TYPE = "SMS";
    private static final String MMS_QUEUE_MESSAGE_TYPE = "MMS";

    @Autowired
    private MeterRegistry meterRegistry;

    @Autowired
    private SmsMessageSource smsMessageSource;

    @Autowired
    private EmailMessageSource emailMessageSource;
    
    private Counter notificationMmsCounter;
    private Counter notificationEmailPlainCounter;
    private Counter notificationEmailAttachmentCounter;
    private Counter notificationMmsPlainCounter;

    /**
     *
     * @param sourceAddress
     * @param destinationAddress
     * @param subject
     * @param emailBody
     * @throws EmailQueueException
     */
    @Override
    public void sendEmail(String sourceAddress, String destinationAddress, String subject, String emailBody) throws EmailQueueException {
        logger.trace("send email NO attachment");
        try {
            emailMessageSource.emailMessage().send(MessageBuilder
                    .withPayload(
                            new EmailMessage(sourceAddress, destinationAddress, subject, emailBody, null))
                    .setHeader("type", EMAIL_NO_ATTACHMENT_MESSAGE_QUEUE_TYPE).build());
            this.notificationEmailPlainCounter.increment();
        } catch (Exception ex) {
            throw new EmailQueueException(I18NResourceBundle.translateForLocale("error.notification.email.unable.to.enqueue.message"), ex);
        }
    }

    /**
     *
     * @param sourceAddress
     * @param destinationAddress
     * @param subject
     * @param emailBody
     * @param attachmentReferences
     * @throws EmailQueueException
     */
    @Override
    public void sendEmailWithAttachment(String sourceAddress, String destinationAddress, String subject, String emailBody, List<String> attachmentReferences) throws EmailQueueException {
        logger.trace("send email With attachment");
        try {
            emailMessageSource.emailMessage().send(MessageBuilder
                    .withPayload(
                            new EmailMessage(sourceAddress, destinationAddress, subject, emailBody, attachmentReferences))
                    .setHeader("type", EMAIL_WITH_ATTACHMENT_QUEUE_MESSAGE_TYPE).build());

            this.notificationEmailAttachmentCounter.increment();
        } catch (Exception ex) {
            throw new EmailQueueException(I18NResourceBundle.translateForLocale("error.notification.email.unable.to.enqueue.message"), ex);
        }
    }

    /**
     *
     * @param sourceNumber
     * @param destinationNumber
     * @param message
     * @throws SmsQueueException
     */
    @Override
    public void sendSmsMessage(String sourceNumber, String destinationNumber, String message) throws SmsQueueException {
        try {
            smsMessageSource.smsMessage().send(MessageBuilder
                    .withPayload(
                            new SmsMessage(sourceNumber, destinationNumber, message))
                    .setHeader("type", SMS_QUEUE_MESSAGE_TYPE).build());
            this.notificationEmailAttachmentCounter.increment();
        } catch (Exception ex) {
            throw new SmsQueueException(I18NResourceBundle.translateForLocale("error.notification.sms.unable.to.enqueue.message"), ex);
        }
    }

    /**
     *
     * @param sourceNumber
     * @param destinationNumber
     * @param message
     * @param imageRef
     * @throws SmsQueueException
     */
    @Override
    public void sendMmsMessage(String sourceNumber, String destinationNumber, String message, List<String> imageRef) throws SmsQueueException {
        try {
            smsMessageSource.smsMessage().send(MessageBuilder
                    .withPayload(
                            new MmsMessage(sourceNumber, destinationNumber, message, imageRef))
                    .setHeader("type", MMS_QUEUE_MESSAGE_TYPE).build());
            notificationMmsCounter.increment();
        } catch (Exception ex) {
            throw new SmsQueueException(I18NResourceBundle.translateForLocale("error.notification.sms.unable.to.enqueue.message"), ex);
        }
    }

    @PostConstruct
    private void initializeMetrics() {
        notificationEmailPlainCounter = Counter.builder("notification.email.plain.send")
                
                .description("The number of plain email messages the service has enqueued.")
                .register(meterRegistry);

        notificationEmailAttachmentCounter = Counter.builder("notification.email.attachment.send")
                
                .description("The number of email messages with attachments the service has enqueued.")
                .register(meterRegistry);
        
        this.notificationEmailPlainCounter = Counter.builder("notification.sms.send")
                
                .description("The number of plain SMS messages the service has enqueued.")
                .register(meterRegistry);

        notificationMmsCounter = Counter.builder("notification.mms.send")
                
                .description("The number of MMS messages the service has enqueued.")
                .register(meterRegistry);

        
    }

}
