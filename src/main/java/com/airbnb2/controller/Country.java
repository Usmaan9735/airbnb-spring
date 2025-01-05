package com.airbnb2.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/countries")
public class Country {
    @PostMapping("/addCountry")
    public String addCountry(){
        return "done";
    }
}
