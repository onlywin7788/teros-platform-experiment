package com.example.demo.router;

import com.example.demo.handler.WebFluxHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;




@Configuration
public class WebFluxRouter {


    // 경로 입력받기
    @Bean
    public RouterFunction<ServerResponse> test3(WebFluxHandler webFluxHandler){
        return RouterFunctions.route(RequestPredicates.GET("/{name}")
                        .and(RequestPredicates.accept(MediaType.TEXT_HTML)),
                webFluxHandler::filtertest);
    }


    @Bean
    public RouterFunction<ServerResponse> index1(WebFluxHandler webFluxHandler){
        return RouterFunctions.route(RequestPredicates.GET("/")
                        .and(RequestPredicates.accept(MediaType.TEXT_HTML)),
                webFluxHandler::index);
    }

    @Bean
    public RouterFunction<ServerResponse> index2(WebFluxHandler webFluxHandler){
        return RouterFunctions.route(RequestPredicates.GET("/index")
                        .and(RequestPredicates.accept(MediaType.TEXT_HTML)),
                webFluxHandler::index);
    }

    @Bean
    public RouterFunction<ServerResponse> hello(WebFluxHandler webFluxHandler){
        return RouterFunctions.route(RequestPredicates.GET("/hello")
                        .and(RequestPredicates.accept(MediaType.TEXT_HTML)),
                webFluxHandler::hello);
    }


    @Bean
    public RouterFunction<ServerResponse> test(WebFluxHandler webFluxHandler){
        return RouterFunctions.route(RequestPredicates.GET("/test")
                        .and(RequestPredicates.accept(MediaType.TEXT_HTML)),
                webFluxHandler::babyList);
    }



}
