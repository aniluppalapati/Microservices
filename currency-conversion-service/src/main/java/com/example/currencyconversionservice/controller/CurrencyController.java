package com.example.currencyconversionservice.controller;

import com.example.currencyconversionservice.configuration.CurrencyExchangeProxy;
import com.example.currencyconversionservice.model.CurrencyConversion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@RestController
public class CurrencyController {

    @Autowired
    CurrencyExchangeProxy currencyExchangeProxy;
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversion calculateCurrencyConversion(@PathVariable String from,
                                                          @PathVariable String to,
                                                          @PathVariable BigDecimal quantity){
        Map<String, String> uriVariables = new HashMap<>();
        uriVariables.put("from",from);
        uriVariables.put("to",to);

        ResponseEntity<CurrencyConversion> forEntity = restTemplate.getForEntity("http://localhost:8000/currencyexchange/from/{from}/to/{to}", CurrencyConversion.class, uriVariables);

        CurrencyConversion entityBody = forEntity.getBody();

        return new CurrencyConversion(entityBody.getId(),from,to,
                quantity,entityBody.getConversionMultiple(),quantity.multiply(entityBody.getConversionMultiple()),entityBody.getEnvironment()+" Rest Template");
    }


    @GetMapping("/currency-conversion-feign/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversion feigncalculateCurrencyConversion(@PathVariable String from,
                                                          @PathVariable String to,
                                                          @PathVariable BigDecimal quantity){

        CurrencyConversion entityBody =currencyExchangeProxy.fetchCurrencyDetails(from,to);

        return new CurrencyConversion(entityBody.getId(),from,to,
                quantity,entityBody.getConversionMultiple(),quantity.multiply(entityBody.getConversionMultiple()),entityBody.getEnvironment()+" Feign");
    }




}
