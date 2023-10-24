package com.example.currencyexchangeservice.controller;

import com.example.currencyexchangeservice.model.CurrencyExchange;
import com.example.currencyexchangeservice.repository.CurrencyExchangeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyExchangeController {

    @Autowired
    private CurrencyExchangeRepository currencyExchangeRepository;

    @Autowired
    private Environment environment;

    @GetMapping("currencyexchange/from/{fromCurrency}/to/{toCurrency}")
    public CurrencyExchange fetchCurrencyDetails(@PathVariable String fromCurrency,@PathVariable String toCurrency){

        CurrencyExchange currencyExchange=currencyExchangeRepository.findByFromAndTo(fromCurrency,toCurrency);
        if(currencyExchange==null){
            new RuntimeException("Conversion from "+fromCurrency+" to "+toCurrency+" is not Possible");
        }
        String port = environment.getProperty("local.server.port");
        currencyExchange.setEnvironment(port);
        return currencyExchange;
    }



}
