package com.javatar.yearsservice.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info = @Info(title = "Years Service", description = "REST API for CRUD operation", version = "1.0"))
public class OpenApiConfig {

}
