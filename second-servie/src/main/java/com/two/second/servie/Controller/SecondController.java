package com.two.second.servie.Controller;


import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/second")
public class SecondController {


    @GetMapping("/two")
    public Map<String, Object> normalHit(){
        return Map.of(
                "hit","successful",
                "now","Hitting",
                "TimeStamp", Instant.now()
        );
    }

    @PostMapping("/three")
    public Map<String, Object> postHit(@RequestBody HashMap<String, Object> map){
        return map;
    }



}