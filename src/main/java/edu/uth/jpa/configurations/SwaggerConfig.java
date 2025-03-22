package edu.uth.jpa.configurations;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

//x   @Bean
    public OpenAPI customOpenAPI() {
    return new OpenAPI()
            .info(new Info().title("Spring Boot API with Swagger")
                    .version("1.0")
                    .description("This is a sample Spring Boot RESTful API with Swagger"));
    }

}
