package com.hiynn.dynamic.datasource.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
* @Description  只有在开发测试环境测这个配置类才会被加载
* @Author ZhouXiaoLe
* @Date  2019/7/18  14:37
* @Param
* @return
**/
@Profile({"dev","test"})
@Configuration
@EnableSwagger2
public class Swagger2Config {
    /**
    * @Description Swagger2配置
    * @Author ZhouXiaoLe
    * @Date  2019/7/18  14:36
    * @Param []
    * @return springfox.documentation.spring.web.plugins.Docket
    **/
    @Bean
    public Docket createRestApi() {
        ParameterBuilder ticketPar = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<>();
        ticketPar.name("Authorization").description("token")
                .modelRef(new ModelRef("string")).parameterType("header")
                .required(false).build();
        pars.add(ticketPar.build());
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.hiynn.dynamic.datasource.controller"))
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(pars);
    }

    /**
    * @Description  构建 api文档的详细信息函数
    * @Author ZhouXiaoLe
    * @Date  2019/7/18  14:36
    * @Param []
    * @return springfox.documentation.service.ApiInfo
    **/
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                //页面标题
                .title("Spring Boot 开发使用 Swagger2 构建RESTful API")
                //创建人
                .contact(new Contact("hiynn", "http://www.baidu.com", ""))
                //版本号
                .version("1.0")
                //描述
                .description("API 描述")
                .build();
    }
}
