package com.f.configure;

import com.f.base.Auth;
import com.google.common.collect.Lists;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.List;

/**
 * User: bvsoo
 * Date: 2018/6/28
 * Time: 下午4:48
 */
@Configuration
public class SwaggerConfigure {


    @Bean
    public Docket createRestApi() {
        ParameterBuilder tokenPar = new ParameterBuilder();
        tokenPar.name(Auth.JWT_HEADER_KEY).description("token").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
        List<Parameter> pars = Lists.newArrayList();
        pars.add(tokenPar.build());
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.f.mvc.action"))
                .paths(PathSelectors.any())
                .build().globalOperationParameters(pars).apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Admin Server api")
                .description("")
                .termsOfServiceUrl("")
                .version("1.0")
                .build();
    }
}
