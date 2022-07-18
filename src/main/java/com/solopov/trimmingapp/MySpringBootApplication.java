package com.solopov.trimmingapp;

import com.solopov.postprocecessors.annotation.EnableStringTrimming;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;


@SpringBootApplication
@EnableStringTrimming
public class MySpringBootApplication {

    @Autowired
    StringTrimmingRelatedService service;

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(MySpringBootApplication.class, args);
        var printerUser = context.getBean(StringTrimmingRelatedService.class);


        System.out.println("\""+printerUser.getStringValue()+"\"");
        System.out.println("\""+printerUser.getStringValue("   I have used string with whitespaces  ")+"\"");
    }

}
