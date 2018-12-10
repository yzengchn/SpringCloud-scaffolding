package xyz.yzblog.weather.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


/**
 * 如上代码所示，通过@Configuration注解，让Spring来加载该类配置。再通过@EnableSwagger2注解来启用Swagger2。
 * 再通过createRestApi函数创建Docket的Bean之后，apiInfo()用来创建该Api的基本信息（这些基本信息会展现在文档页面中）。
 * select()函数返回一个ApiSelectorBuilder实例用来控制哪些接口暴露给Swagger来展现，
 * 本例采用指定扫描的包路径来定义，Swagger会扫描该包下所有Controller定义的API，并产生文档内容（除了被@ApiIgnore指定的请求）。
 * 地址 ： http://localhost:8080/swagger-ui.html
 * 通过@ApiOperation注解来给API增加说明、
 * 通过@ApiImplicitParams、@ApiImplicitParam注解来给参数增加说明。
 * @author Yao.Zeng
 *
 */


@Configuration
@EnableSwagger2
public class Swagger2 {
	
	@Value("${swagger.scan-package}")
	private String SCAN_PACKAGE;
	@Value("${swagger.is.enable}")
	private boolean SWAGGER_IS_ENABLE;
	
	@Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
        		.enable(SWAGGER_IS_ENABLE)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage(SCAN_PACKAGE))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("RESTful APIs")
                .description("更多技术资料请访问：<a href='https://www.yzblog.xyz/'>我的博客</a>")
                .termsOfServiceUrl("https://yzblog.xyz/")
                .contact("@Email：yzengchn@163.com")
                .version("9.9")
                .build();
    }

}
