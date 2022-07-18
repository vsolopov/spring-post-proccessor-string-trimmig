package com.solopov.postprocecessors.configuration;

import com.solopov.postprocecessors.TrimmedAnnotationBeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StringTrimmingConfiguration {

    @Bean
    public TrimmedAnnotationBeanPostProcessor stringTrimmingPostProcessor(){
        return new TrimmedAnnotationBeanPostProcessor();
    }

}
