
package com.thinkmicroservices.ri.spring.notification.service;

/**
 *
 * @author cwoodward
 */
public class EmailQueueException extends Exception {
    /**
     * 
     * @param msg 
     */
    public EmailQueueException(String msg){
        super(msg);
    }
    /**
     * 
     * @param t 
     */
    public EmailQueueException(Throwable t){
        super(t);
    }
    /**
     * 
     * @param msg
     * @param t 
     */
    public EmailQueueException(String msg, Throwable t){
        super(msg,t);
    }
}
