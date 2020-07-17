 
package com.thinkmicroservices.ri.spring.notification.messaging.message;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author cwoodward
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmailMessage {
    private String sourceAddress;
    private String destinationAddress;
    private String subject;
    private String body;
    List<String> attachmentReferences;
            
}
