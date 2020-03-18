package com.demo.spring.reactive.component;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

//@Controller
@Configuration
public class RouterFunctionConfig {
    @Bean
    RouterFunction<ServerResponse> routerFunction(HandlerFunction handlerFunction) {
        return RouterFunctions
                .route(GET("/functional/mono")
                        .and(accept(MediaType.APPLICATION_JSON)), handlerFunction::monoServer)
                .andRoute(GET("/functional/flux")
                        .and(accept(MediaType.APPLICATION_JSON)), handlerFunction::fluxServer);
    }
}
