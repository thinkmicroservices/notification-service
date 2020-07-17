
package com.thinkmicroservices.ri.spring.notification.controller;

import com.thinkmicroservices.ri.spring.notification.model.EmailRequest;
import com.thinkmicroservices.ri.spring.notification.model.MmsRequest;
import com.thinkmicroservices.ri.spring.notification.model.SmsRequest;
import com.thinkmicroservices.ri.spring.notification.service.EmailQueueException;
import com.thinkmicroservices.ri.spring.notification.service.NotificationService;
import com.thinkmicroservices.ri.spring.notification.service.SmsQueueException;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
 
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author cwoodward
 */
@RestController
@Slf4j
public class NotificationController {

     
    @Autowired
    private NotificationService notificationService;

    
    /**
     * 
     * @param request
     * @return 
     */
    @RequestMapping(value = "/sendEmail", method = {RequestMethod.POST})
    @ResponseBody
    @ApiOperation(value = "sendEmail", response = List.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully sent email message")
        ,
        @ApiResponse(code = 500, message = "An error ocurred sending the email.")
    })

    
    public ResponseEntity<?> sendEmailMessage(@RequestBody EmailRequest request) {
        log.debug("send an Email message");
        try {

            if ((request.getAttachmentReferences() == null) || (request.getAttachmentReferences().isEmpty())) {
                notificationService.sendEmail(request.getSourceAddress(), request.getDestinationAddress(), request.getSubject(), request.getBody());
            } else {

                notificationService.sendEmailWithAttachment(request.getSourceAddress(), request.getDestinationAddress(), request.getSubject(), request.getBody(),request.getAttachmentReferences());
            }
            return ResponseEntity.ok(request);
        } catch (EmailQueueException ex) {
            log.error("error sending email message", ex);
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

 
    /**
     * 
     * @param request
     * @return 
     */
    @ApiOperation(value = "send an MMS message")
    @RequestMapping(value = "/sendMMS", method = {RequestMethod.POST})
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully sent MMS message")
        ,
        @ApiResponse(code = 500, message = "An error ocurred sending the MMS message.")
    })
    @ResponseBody
    public ResponseEntity<?> sendMMSMessage(@RequestBody MmsRequest request) {
        log.debug("send a MMS message");

        try {

            if ((request.getAttachmentList() == null) || (request.getAttachmentList().isEmpty())) {
                notificationService.sendSmsMessage(request.getSourceNumber(), request.getDestinationNumber(), request.getMessage());
            } else {
                notificationService.sendMmsMessage(request.getSourceNumber(), request.getDestinationNumber(), request.getMessage(), request.getAttachmentList());
            }
            return ResponseEntity.status(HttpStatus.ACCEPTED).build();
        } catch (SmsQueueException ex) {
            log.error("error sending MMS message", ex);
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
