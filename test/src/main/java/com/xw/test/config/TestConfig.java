package com.xw.test.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "pamcs")
public class TestConfig {

    private List<String> test;

    public List<String> getTest() {
        return test;
    }

    public void setTest(List<String> test) {
        this.test = test;
    }
}
