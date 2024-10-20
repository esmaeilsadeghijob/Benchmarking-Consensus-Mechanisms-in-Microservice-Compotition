package com.javatar.crdservice.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info = @Info(title = "Crd Server", description = "REST API for managing transaction", version = "1.0"))
public class OpenApiConfig {

}
