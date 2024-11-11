package com.token.config;

import com.token.interceptor.AdminJwtTokenInterceptor;
import com.token.interceptor.CommonJwtTokenInterceptor;
import com.token.json.JacksonObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.List;

/**
 * 配置类，注册web层相关组件
 */
@Configuration
@Slf4j
public class WebMvcConfiguration extends WebMvcConfigurationSupport {

    @Autowired
    private AdminJwtTokenInterceptor adminJwtTokenInterceptor;
    @Autowired
    private CommonJwtTokenInterceptor commonJwtTokenInterceptor;

    /**
     * 注册自定义拦截器
     * @param registry
     */
    protected void addInterceptors(InterceptorRegistry registry) {
        log.info("开始注册自定义拦截器...");
        registry.addInterceptor(adminJwtTokenInterceptor)
                .addPathPatterns("/admin/**")
                .excludePathPatterns("/admin/employee/login");
        registry.addInterceptor(commonJwtTokenInterceptor)
                .addPathPatterns("/common/**");
    }

    /**
     * 通过knife4j生成接口文档 user模块 admin模块
     *
     * @return
     */
    @Bean
    public Docket docketUser() {
        ApiInfo apiInfo = new ApiInfoBuilder()
                .title("token店铺项目接口文档")
                .version("1.0")
                .description("token店铺项目接口文档")
                .build();
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .groupName("用户端接口")
                .apiInfo(apiInfo)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.token.controller.user"))
                .paths(PathSelectors.any())
                .build();
        return docket;
    }

    @Bean
    public Docket docketAdmin() {
        ApiInfo apiInfo = new ApiInfoBuilder()
                .title("token店铺项目接口文档")
                .version("1.0")
                .description("token店铺项目接口文档")
                .build();
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .groupName("管理端接口")
                .apiInfo(apiInfo)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.token.controller.admin"))
                .paths(PathSelectors.any())
                .build();
        return docket;
    }

    @Bean
    public Docket docketCommon() {
        ApiInfo apiInfo = new ApiInfoBuilder()
                .title("token店铺项目接口文档")
                .version("1.0")
                .description("token店铺项目接口文档")
                .build();
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .groupName("通用端接口")
                .apiInfo(apiInfo)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.token.controller.common"))
                .paths(PathSelectors.any())
                .build();
        return docket;
    }

    /**
     * 设置静态资源映射
     *
     * @param registry
     */
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        log.info("swagger接口网站加载...");
        registry.addResourceHandler("/doc.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    /**
     * 时间格式化
     * @param converters
     */
    protected void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        log.info("扩展消息转化器...");
        // 创建一个消息转换器对象
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        // 需要为消息转换器设置一个对象转换器，对象转换器可以将Java对象序列化为json数据
        converter.setObjectMapper(new JacksonObjectMapper());
        // 将自己的消息转化器加入容器中
        converters.add(0,converter);
    }
}