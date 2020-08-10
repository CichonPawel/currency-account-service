package com.currency.account.service.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import springfox.documentation.builders.RequestHandlerSelectors.basePackage
import springfox.documentation.service.ApiInfo
import springfox.documentation.service.Contact
import springfox.documentation.spi.DocumentationType.SWAGGER_2
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2

@Configuration
@EnableSwagger2
@Profile("!test")
private class SwaggerConfiguration() {

    @Bean
    fun api() = Docket(SWAGGER_2)
            .select()
            .apis(basePackage("com.currency.account.service"))
            .build()
            .apiInfo(apiInfo())!!

    private fun apiInfo() = ApiInfo(
            "REST API",
            "Rest api for account service.",
            "0.0.1",
            "",
            Contact("Paweł", "Cicoń", ""),
            "",
            "",
            listOf()
    )

}