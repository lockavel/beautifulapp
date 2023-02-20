package com.qf.beautifulapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2Config {
    @Bean
    public Docket createRestApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                //链式操作
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.qf.beautifulapp.controller"))
                .paths(PathSelectors.any())
                .build();
    }
    //apiInfo指的是接口文档信息
    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("聚美APP的接口文档")
                .description("聚美APP的接口文档")
                .contact(new Contact("刘海龙","","kino0208@163.com"))
                .termsOfServiceUrl("http://localhost:8080")
                .version("1.0")
                .build();
    }

}
