
package com.thinkmicroservices.ri.spring.notification.service;

import java.util.List;

/**
 *
 * @author cwoodward
 */
public interface NotificationService {

    /**
     * 
     * @param sourceAddress
     * @param destinationAddress
     * @param subject
     * @param emailBody
     * @throws EmailQueueException 
     */
    public void sendEmail(String sourceAddress, String destinationAddress, String subject, String emailBody) throws EmailQueueException;

    /**
     * 
     * @param sourceAddress
     * @param destinationAddress
     * @param subject
     * @param body
     * @param attachmentReferences
     * @throws EmailQueueException 
     */
    public void sendEmailWithAttachment(String sourceAddress, String destinationAddress, String subject, String body, List<String> attachmentReferences) throws EmailQueueException ;

    /**
     * 
     * @param sourceNumber
     * @param destinationNumber
     * @param message
     * @throws SmsQueueException 
     */
    public void sendSmsMessage(String sourceNumber, String destinationNumber, String message) throws SmsQueueException;

    /**
     * 
     * @param sourceNumber
     * @param destinationNumber
     * @param message
     * @param imageRef
     * @throws SmsQueueException 
     */
    public void sendMmsMessage(String sourceNumber, String destinationNumber, String message, List<String> imageRef) throws SmsQueueException;

}
