
package com.thinkmicroservices.ri.spring.notification.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author cwoodward
 */
@Data
@NoArgsConstructor
@ApiModel(description = "All details for a SMS request. ")
public class SmsRequest {

    @NotBlank
    @ApiModelProperty(name = "sourceNumber", required = true, value = "The SMS number sending the SMS message.")
    protected String sourceNumber;
    
    @NotBlank
    @ApiModelProperty(name = "destinationNumber", required = true, value = "the SMS number to receive the SMS message.")
    protected String destinationNumber;
    
    @NotBlank
    @Size(min = 1, max = 160)
    @ApiModelProperty(name = "message", required = true, value = "The SMS message body.")
    protected String message;
}
