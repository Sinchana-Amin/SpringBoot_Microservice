package com.swarasinchana.api_gateway.config;

import static org.springframework.cloud.gateway.server.mvc.filter.BeforeFilterFunctions.uri;
import static org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions.route;
import static org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions.http;
import static org.springframework.web.servlet.function.RequestPredicates.path;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

@Configuration
public class GatewayConfig {

   /* @Bean
    RouterFunction<ServerResponse> quizRoute() {

        return route("quiz-service")
                .route(path("/quiz-service/**"), http())
                .before(uri("http://localhost:8082"))
                .build();
    }*/
   @Bean
   RouterFunction<ServerResponse> quizRoute() {
       return route("quiz-service")
               .GET("/quiz-service/**", http())
               .before(uri("http://localhost:8082"))
               .build();
   }
}
