
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
@Data
@ApiModel(description = "All details for a MMS request. ")
public class MmsRequest extends SmsRequest{
    @NotBlank
    @ApiModelProperty( name="attachmentList", required=true, value="List of publicly accessible URLs to attach to the SMS." )
    private List<String> attachmentList;
}
