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
     /*   ParameterBuilder ticketPar = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<Parameter>();
        ticketPar.name("Authorization").description("token")
                .modelRef(new ModelRef("string")).parameterType("header")
                .required(true).build();
        pars.add(ticketPar.build());*/
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                        .title("dh-mall电商系统接口文档")
                        .termsOfServiceUrl("francis")
                        .version("1.0")
                        .build())
//                .globalOperationParameters(pars)
                .select()
                .paths(Predicate.not(PathSelectors.regex("/error.*")))
                .paths(PathSelectors.any())
                .build();
        return docket;
    }
}
