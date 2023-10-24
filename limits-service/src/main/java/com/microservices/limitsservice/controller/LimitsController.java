package com.microservices.limitsservice.controller;

import com.microservices.limitsservice.configuration.LimitsProperties;
import com.microservices.limitsservice.model.Limits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LimitsController {

    @Autowired
    private LimitsProperties properties;

    @GetMapping("/v1/limits")
    public Limits getLimits(){
        return new Limits(properties.getMinimum(),properties.getMaximum());
    }


}
