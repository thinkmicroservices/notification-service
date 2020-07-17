package com.thinkmicroservices.ri.spring.notification;

import java.util.Arrays;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 *
 * @author cwoodward
 */
@Profile(value = {"swagger"})
@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    /**
     * 
     * @return 
     */
    @Bean
    public Docket productApi() {

        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.thinkmicroservices.ri.spring.notification.controller"))
                .build()
                .globalOperationParameters(
                        Arrays.asList(new ParameterBuilder()
                                .name("Accept-Language")
                                .description("used to internationalize the endpoint responses")
                                .modelRef(new ModelRef("string"))
                                .parameterType("header")
                                .required(false).build()))
                .apiInfo(apiEndpointInfo());

    }

    /**
     * 
     * @return 
     */
    private ApiInfo apiEndpointInfo() {
        return new ApiInfoBuilder().title("Notification REST API")
                .description("provides access to notification facilities")
                .license("Apache 2.0")
                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
                .version("1.0.0")
                .build();
    }
}
