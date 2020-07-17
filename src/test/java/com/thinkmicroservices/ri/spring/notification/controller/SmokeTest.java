package com.thinkmicroservices.ri.spring.notification.controller;

 
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * 
 * @author cwoodward
 */
@RunWith(SpringRunner.class)
@SpringBootTest(properties = "spring.cloud.config.enabled=false")

public class SmokeTest {

    @Autowired
    private NotificationController notificationController;

    /**
     * 
     * @throws Exception 
     */
    @Test
    public void contextLoads() throws Exception {
        assertThat(notificationController).isNotNull();
    }
}
