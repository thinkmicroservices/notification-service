package com.thinkmicroservices.ri.spring.notification.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import javax.validation.constraints.NotBlank;
import lombok.Data;

/**
 *
 * @author cwoodward
 */
@ApiModel(description = "All details for an Emailrequest. ")
@Data
public class EmailRequest {

    @NotBlank
    @ApiModelProperty(name = "sourceAddress", required = true, value = "Sender's email address.")
    String sourceAddress;
    
    @NotBlank
    @ApiModelProperty(name = "destinationAddress", required = true, value = "Receiver's email address.")
    String destinationAddress;

    @ApiModelProperty(name = "subject", required = false, value = "Email message subject line.")
    String subject;
    
    @ApiModelProperty(name = "body", required = false, value = "Email message body.")
    String body;
    
    @NotBlank
    @ApiModelProperty(name = "attachmentReferences", required = false, value = "List of publicly accessible URLs to attach to the email.")
    List<String> attachmentReferences;
}
