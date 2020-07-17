
package com.thinkmicroservices.ri.spring.notification.messaging.message;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
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
public class SmsMessage {

    @NotBlank

    protected String sourceNumber;
    @NotBlank

    protected String destinationNumber;
    @NotBlank
    @Size(min = 1, max = 160)
    protected String message;
}
