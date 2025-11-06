package com.example.demo.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

@RestController
@RequestMapping("/three")
public class ThirdController {
    private final String baseUrlForRest="http://ONE/one";

    @Autowired
    private final RestTemplate restTemplate;

    public ThirdController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    @GetMapping("/one/{id}")
    public Map<String, Object> getOne(@PathVariable Long id){
        String extension = "/onehit/{id}";
        String url=baseUrlForRest+extension;
        return restTemplate.getForObject(url,Map.class,id);
    }

    @GetMapping("/with")
    public Map<String, Object> getWith(@RequestHeader String key, @RequestParam String value){
        String url= UriComponentsBuilder.fromHttpUrl(baseUrlForRest+"/secondhit").queryParam("value",value)
                .toUriString();

        HttpHeaders headers = new HttpHeaders();

        headers.add("key",key);

        HttpEntity<Map<String,Object>> httpEntity = new HttpEntity<>(headers);

        ResponseEntity<Map> response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, Map.class);

        return response.getBody();
    }



}