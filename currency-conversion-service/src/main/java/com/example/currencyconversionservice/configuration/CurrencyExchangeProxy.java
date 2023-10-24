package com.example.currencyconversionservice.configuration;

import com.example.currencyconversionservice.model.CurrencyConversion;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@FeignClient(name = "currency-exchange",url = "localhost:8000")
@FeignClient(name = "currency-exchange")
public interface CurrencyExchangeProxy {

    @GetMapping("currencyexchange/from/{fromCurrency}/to/{toCurrency}")
    public CurrencyConversion fetchCurrencyDetails(@PathVariable("fromCurrency") String fromCurrency, @PathVariable("toCurrency") String toCurrency);

}
