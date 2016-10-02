/**
 * Copyright © 2015.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package cn.mypandora.config;

import org.springframework.context.annotation.Bean;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * <p>User: hankaibo
 * <p>Date: 2016/9/24
 * <p>Version: 1.0
 */
//@Configuration
@EnableSwagger2
//@PropertySource("classpath:swagger.properties")
//@ComponentScan(basePackages = "cn.mypandora.controller")
public class SwaggerConfig {

    /**
     * Every Docket bean is picked up by the swagger-mvc framework - allowing for multiple
     * swagger groups i.e. same code base multiple swagger resource listings.
     */
    @Bean
    public Docket customDocket() {
        return new Docket(DocumentationType.SWAGGER_2);
    }

}
