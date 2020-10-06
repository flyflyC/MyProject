package com.fly.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    //多人开发对个分组
    public Docket docket1(){
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("fly");
    }
    //配置Swagger的Docket的bean实例
    @Bean
    public Docket docket(Environment environment){
        //设置要显示swagger的环境
        Profiles profiles=Profiles.of("dev","test");
        //获取项目环境
        boolean flag = environment.acceptsProfiles(profiles);

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                //enable：false关闭swagger
                .enable(flag)
                .groupName("flyfly")
                .select()
                //RequestHandlerSelectors，配置要扫描的接口方式：basePackage：指定要扫描的包
                //any()：扫描全部；none()：不扫描；withClassAnnotation:扫描类上的注解，参数是一个反射对象
                //withMethodAnnotation：扫描方法上的注解
                .apis(RequestHandlerSelectors.basePackage("com.fly.controller"))
                //paths()：过滤路径
                //.paths(PathSelectors.ant("/fly/**"))
                .build();
    }

    //配置swagger的apiInfo信息
    private ApiInfo apiInfo() {
        //作者信息
        Contact DEFAULT_CONTACT = new Contact("flyfly", "", "1903203411@qq.com");
        return new ApiInfo("飞飞的Swagger Api文档",
                "啦啦啦",
                "v1.0",
                "urn:tos",
                DEFAULT_CONTACT,
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList());
    }
}
