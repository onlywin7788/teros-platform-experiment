package com.example.demo.handler;

import java.util.HashMap;
import java.util.Map;

import com.example.demo.vo.WebFluxVO;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Component
public class WebFluxHandler {


    //  path 데이터 입력받아서 대문자로 변환 후 반환
    public Mono<ServerResponse> filtertest(ServerRequest request) {
        String req = request.path();
        Flux<String> aaa = Flux.just(req).map(String::toLowerCase);

        return ServerResponse.ok()
                .contentType(APPLICATION_JSON)
                .body(aaa, Flux.class);
    }

    public Mono<ServerResponse> index(ServerRequest request){

        final Map<String, WebFluxVO> data = new HashMap<>();
        data.put("data", new WebFluxVO("", 0));
        Mono<ServerResponse> index = ServerResponse.ok().contentType(MediaType.TEXT_HTML).render("index", data);
        System.out.println(request);

        return ServerResponse.ok().contentType(MediaType.TEXT_HTML).render("index", data);
    }

    public Mono<ServerResponse> hello(ServerRequest request){
        final Map<String, WebFluxVO> data = new HashMap<>();
        data.put("data", new WebFluxVO("psw", 32));
        return ServerResponse.ok().contentType(MediaType.TEXT_HTML).render("index", data);
    }


    public Mono<ServerResponse> babyList(ServerRequest request) {
        Flux<WebFluxVO> babyFlux = Flux.just(new WebFluxVO("mj", 1));
        return ServerResponse.ok()
                .contentType(APPLICATION_JSON)
                .body(babyFlux, WebFluxVO.class);
    }





}
