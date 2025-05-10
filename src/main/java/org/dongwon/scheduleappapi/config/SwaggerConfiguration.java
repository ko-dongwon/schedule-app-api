package org.dongwon.scheduleappapi.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("nbcamp 일정 관리 앱")
                        .description("일정 관리 앱 API 문서입니다.")
                        .version("1.0.0"));
    }
}
