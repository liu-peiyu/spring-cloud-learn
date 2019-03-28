package com.geekcattle.ribbon.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Random;

@RestController
@RequestMapping(value = "/ribbon")
public class RibbonController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "backHandler")
    @GetMapping("client")
    public String getUserService() throws Exception {

        long sleepTime = new Random().nextInt(3000);
        logger.info("sleepTime:{}ms",sleepTime);
        Thread.sleep(sleepTime);
        long start = System.currentTimeMillis();
        String result = restTemplate.getForObject("http://user-service/index", String.class);
        long end = System.currentTimeMillis();
        logger.info("spend time:{}ms",(end - start));
        return result;
    }

    private String backHandler(){
        return "error";
    }

}
