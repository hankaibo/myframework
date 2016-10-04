/**
 * Copyright © 2015.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package cn.mypandora.config;

import com.google.common.base.Predicate;
import com.google.common.collect.Sets;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static com.google.common.base.Predicates.or;
import static springfox.documentation.builders.PathSelectors.regex;

/**
 * <p>User: hankaibo
 * <p>Date: 2016/9/24
 * <p>Version: 1.0
 */
//@Configuration
@EnableSwagger2
//@ComponentScan(basePackages = "cn.mypandora.controller")
public class SwaggerConfig {

    @Bean
    public Docket userApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("user")
                .forCodeGeneration(true)
                .select()
                .paths(or(regex("/users/.*")))
                .build()
                .apiInfo(apiInfo());
    }

    @Bean
    public Docket deptApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("depts")
                .forCodeGeneration(true)
                .pathMapping("/")// base，最终调用接口后会和paths拼接在一起
                .select()
                .paths(or(regex("/depts/.*")))
                .build()
                .apiInfo(apiInfo());
    }

    @Bean
    public Docket logApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("log")
                .forCodeGeneration(false)
                .pathMapping("/")
                .select()
                .paths(or(regex("/logs/.*")))
                .build()
                .apiInfo(apiInfo());
    }

    @Bean
    public Docket resApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("resources")
                .forCodeGeneration(false)
                .pathMapping("/")
                .select()
                .paths(or(regex("/resources/.*")))
                .build()
                .apiInfo(apiInfo());
    }

    @Bean
    public Docket loginLogoutApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("loginOut")
                .forCodeGeneration(false)
                .select()
                .paths(loginOutPaths())
                .build()
                .apiInfo(apiInfo());
    }

    private Predicate<String> loginOutPaths() {
        return or(
                regex("/login/.*"),
                regex("/logout/.*")
        );
    }

    @Bean
    public Docket allApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .produces(Sets.newHashSet("application/json"))
                .consumes(Sets.newHashSet("application/json"))
                .protocols(Sets.newHashSet("http", "https"))
                .forCodeGeneration(true)
                .select().paths(regex(".*"))
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("myframework REST API")
                .description("基本接口API示例")
                .version("v1.0")
                .termsOfServiceUrl("http://localhost:8080/swagger-ui.html/")
                .contact(new Contact("hankaibo", "www.l.cn", "hankaibo@hotmail.com"))
                .license("MIT")
                .licenseUrl("https://github.com/springfox/springfox/blob/master/LICENSE")
                .build();
    }

}
