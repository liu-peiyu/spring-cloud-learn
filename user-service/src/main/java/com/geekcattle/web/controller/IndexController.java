package com.geekcattle.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class IndexController {

    private final Logger logger = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    private DiscoveryClient client;

    @GetMapping("/index")
    public String index() {
        List<String> serviceList = client.getServices();
        serviceList.stream().forEach(service -> {
            logger.info("CurrentThread：{} service: {}", Thread.currentThread().getName(), service);
        });

        return "success";
    }

    @GetMapping("/user")
    public String getUser(){
        return "user";
    }

    @GetMapping("/getInstances")
    public String getInstance(@RequestParam("serviceid") String serviceId) {
        List<ServiceInstance> instances = client.getInstances(serviceId);
        StringBuilder str = new StringBuilder();
        instances.stream().forEach(serviceInstance -> {
            str.append(System.getProperty("line.separator"));
            str.append(String.format("currentThread: %s, getServiceId: %s, getHost: %s",Thread.currentThread().getName(),serviceInstance.getServiceId(),serviceInstance.getHost()));
        });
        logger.info(str.toString());
        return str.toString();
    }

}
