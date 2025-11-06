package com.example.demo.Configuration;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean("oneWebClient")
    public WebClient oneWebClient(){
        return WebClient.builder().baseUrl("http://ONE/one").build();
    }

    @Bean("twoWebClient")
    public WebClient twoWebClient(){
        return WebClient.builder().baseUrl("http://TWO/second").build();
    }

    @Bean
    @LoadBalanced
    public WebClient.Builder lbWebClientBuilder(){
        return WebClient.builder();
    }

}