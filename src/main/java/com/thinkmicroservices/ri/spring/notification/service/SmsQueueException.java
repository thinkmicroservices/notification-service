
package com.thinkmicroservices.ri.spring.notification.service;

/**
 *
 * @author cwoodward
 */
public class SmsQueueException extends Throwable {

    /**
     * 
     * @param msg 
     */
    public SmsQueueException(String msg) {
        super(msg);
    }

    /**
     * 
     * @param t 
     */
    public SmsQueueException(Throwable t) {
        super(t);
    }

    /**
     * 
     * @param msg
     * @param t 
     */
    public SmsQueueException(String msg, Throwable t) {
        super(msg, t);
    }
}
