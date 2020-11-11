package com.go.fluent.product.item.service;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@EnableJpaRepositories(basePackages = "com.go.fluent.product.item.service.repository")
//@Configuration
@EnableEurekaClient
@EnableSwagger2
//@EnableWebMvc
//@EnableEurekaClient

@SpringBootApplication
public class ProductItemServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductItemServiceApplication.class, args);
    }
//    @Override
//    protected SpringApplicationBuilder configure(
//            SpringApplicationBuilder application) {
//        return application.sources(ShoppingManagementApplication.class);
//    }

    @Bean
    Docket productItemApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.go.fluent.product.item.service.resource"))
                .paths(paths())
                .build();
    }

     Predicate<String> paths() {

        return Predicates.and(
                PathSelectors.regex("/.*"),
                Predicates.not(PathSelectors.regex("/error.*")));
    }

    @Bean
    MapperFacade mapperFacade1() {
        return new DefaultMapperFactory.Builder().build().getMapperFacade();
    }
//
     ApiInfo apiInfo() {
        return new ApiInfo(
                "Product Item Service",
                "Product Item Service API",
                "Product Item Service 1.0",
                "Terms of service",
                "Eric Jason C. Salivio",
                "ericsalivio.ccs@gmail.com",
                "sample-url.com");
    }

}
