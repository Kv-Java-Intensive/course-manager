//package com.itacademy.cms;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import springfox.documentation.builders.ApiInfoBuilder;
//import springfox.documentation.builders.PathSelectors;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.service.ApiInfo;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.web.plugins.Docket;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;
//
//@Configuration
//@EnableSwagger2
//public class SpringSwaggerConfig {
//  @Bean
//  public Docket api() {
//    return new Docket(DocumentationType.SWAGGER_2)
//        .select()
//        .apis(RequestHandlerSelectors.basePackage("com.itacademy"))
//        .paths(PathSelectors.any())
//        .build()
//        .apiInfo(apiInfo());
//  }
//
//  private ApiInfo apiInfo() {
//    return new ApiInfoBuilder().title("Course manager").version("1.0.0")
//        .description(
//            "Created by softservians: Olga Demenko, Serhii Honchar, Volodymyr Koniukh,  Nikita Kosenko, Sasha Tiutiunnyk, Yarik Voitiuk, Ivan Zaborovets")
//        .build();
//  }
//}
