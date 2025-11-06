package com.One.first.servie.Controller;

import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.Map;

@RestController
@RequestMapping("/one")
public class OneController {

    @GetMapping("/onehit/{id}")
    public Map<String, Object> hitWithVariable(@PathVariable Long id){
        return Map.of(
                "Noting","Noting",
                "output",id,
                "time Stamp", Instant.now().toString()
        );
    }

    @GetMapping("/secondhit")
    public Map<String,Object> hitForResponse(@RequestHeader String key,@RequestParam String value){
        return Map.of(
                "Hitting",key,
                "value",value,
                "timestamp",Instant.now()
        );
    }
}
