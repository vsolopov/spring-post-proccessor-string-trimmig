package com.solopov.trimmingapp;

import com.solopov.postprocecessors.annotation.Trimmed;
import org.springframework.stereotype.Service;

@Service
@Trimmed
public class StringTrimmingRelatedService {


    public String getStringValue(String value){
        return value;
    }


    public String getStringValue(){
        return "    I'm string with whitespaces   ";
    }

}
