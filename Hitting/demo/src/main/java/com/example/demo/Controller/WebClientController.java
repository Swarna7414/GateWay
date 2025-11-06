package com.example.demo.Controller;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.util.Map;

@RestController
@RequestMapping("web")
public class WebClientController {

    private WebClient oneWebClient;

    private WebClient twoWebClient;


    public WebClientController(@Qualifier("oneWebClient") WebClient oneWebClient,@Qualifier("twoWebClient") WebClient twoWebClient) {
        this.oneWebClient = oneWebClient;
        this.twoWebClient = twoWebClient;
    }


    @GetMapping("/first/{id}")
    public Mono<Map<String, Object>> hitFirstService(@PathVariable Long id){
        return oneWebClient.get().
                uri(uriBuilder -> uriBuilder.path("/onehit/{id}").build(id)).
                retrieve().bodyToMono(new ParameterizedTypeReference<Map<String, Object>>() {});
    }


    @GetMapping("/second")
    public Mono<Map<String, Object>> hitWithParameter(@RequestParam String value, @RequestHeader String key){
        return oneWebClient.get().uri(uriBuilder -> uriBuilder.path("/secondhit").queryParam("value",value).build())
                .header("key",key)
                .retrieve().bodyToMono(new ParameterizedTypeReference<Map<String, Object>>() {});
    }

    @GetMapping("/two")
    public Mono<Map<String, Object>> hitSecondService(){
        return twoWebClient.get().uri(uriBuilder -> uriBuilder.path("/secondhit").build()).retrieve().bodyToMono(new ParameterizedTypeReference<Map<String, Object>>() {});
    }

    @PostMapping("/three")
    public Mono<Map<String, Object>> hitWithQueryParam(){
        Map<String, Object> map= Map.of(
                "something","SomeOne",
                "who","are",
                "you", Instant.now()
                );

        return twoWebClient.post().uri(uriBuilder -> uriBuilder.path("/three").build()).header("Content-Type","application/json")
                .bodyValue(map).retrieve().bodyToMono(new ParameterizedTypeReference<Map<String, Object>>() {});
    }

}