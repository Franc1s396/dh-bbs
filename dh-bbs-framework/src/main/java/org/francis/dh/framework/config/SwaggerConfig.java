package org.francis.dh.framework.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

import java.util.function.Predicate;

/**
 * @author Franc1s
 * @date 2022/5/29
 * @apiNote
 */
@Configuration
@EnableSwagger2WebMvc
public class SwaggerConfig {
    @Bean
    public Docket docket() {
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                        .title("dh-bbs论坛后台管理系统接口文档")
                        .termsOfServiceUrl("francis")
                        .version("1.0")
                        .build())
                .select()
                .paths(PathSelectors.regex("/error.*").negate())
                .paths(PathSelectors.any())
                .build();
        return docket;
    }
}
