package com.samax.simpleCommerce.common.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/health")
public class HealthController {

    @GetMapping("/test")
    String health() {
        return "ok";
    }

}
