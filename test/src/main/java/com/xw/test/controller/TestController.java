package com.xw.test.controller;

import com.xw.test.config.TestConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private TestConfig testConfig;

    @GetMapping("/test")
    public String test() {
        System.out.println(testConfig.getTest());
        return "test";
    }

}
