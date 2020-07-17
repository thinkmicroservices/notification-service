
package com.thinkmicroservices.ri.spring.notification.controller;



import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author cwoodward
 */
@Profile("!swagger")
@RestController
 
public class DisableSwaggerController {

    /**
     * 
     * @param httpResponse
     * @throws IOException 
     */
    @RequestMapping(value = "swagger-ui.html", method = RequestMethod.GET)
    public void getSwagger(HttpServletResponse httpResponse) throws IOException {
        httpResponse.setStatus(HttpStatus.NOT_FOUND.value());
    }
}
