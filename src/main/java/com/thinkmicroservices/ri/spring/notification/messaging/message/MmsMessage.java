
package com.thinkmicroservices.ri.spring.notification.messaging.message;

import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author cwoodward
 */
@Data
 
 
@NoArgsConstructor
public class MmsMessage extends SmsMessage{
    private List<String> attachmentList;
    
    public MmsMessage(String sourceNumber,String destinationNumber,String message, List<String> attachmentList){
        super(sourceNumber,destinationNumber,message);
        this.attachmentList=attachmentList;
    }
}
