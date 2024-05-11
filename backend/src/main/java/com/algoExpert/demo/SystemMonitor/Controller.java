package com.algoExpert.demo.SystemMonitor;

import com.algoExpert.demo.Admin.Entity.FeatureUsage;
import com.algoExpert.demo.Admin.AdminEnums.FeatureType;
import com.algoExpert.demo.Admin.Repository.Service.FeatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/features")
@RestController
public class Controller {
    @Autowired
    private  CustomHttpTraceRepository requestCounter;

    @Autowired
    private FeatureService featureService;



    @GetMapping("/my-endpoint")
    public String myEndpoint() {

        requestCounter.incrementRequestCount("");

        long requestCount = requestCounter.getRequestCount("");

        return "Hello from my endpoint! "+requestCount;
    }

    @GetMapping("/test")
    public FeatureUsage feature() {
        return  featureService.updateFeatureCount(FeatureType.COMMENT);
    }

    @GetMapping("/featureUsage")
    public FeatureUsage featureUsage() {
        return featureService.findByFeatureType(FeatureType.COMMENT);
    }





    @GetMapping("/my-feature")
    public String myFeature() {
        // Business logic for your feature
        requestCounter.incrementFeatureUsage();
        return "my-feature";
    }
}
