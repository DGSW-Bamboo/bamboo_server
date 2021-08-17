package com.bamboo.api.global.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

  @Bean
  public Docket swaggerAPI () {
  return new Docket(DocumentationType.SWAGGER_2)
          .select()
          .apis(RequestHandlerSelectors.any())
          .paths(PathSelectors.any())
          .build()
          .useDefaultResponseMessages(false);
  }

  private ApiInfo swaggerInfo() {
    return new ApiInfoBuilder().title("Bamboo Server API Documentation")
      .description("2021 Bamboo server의 API문서 입니다")
      .license("DGSW-BAMBOO").licenseUrl("https://github.com/DGSW-Bamboo/bamboo_server")
      .version("1")
      .build();
  }
}
