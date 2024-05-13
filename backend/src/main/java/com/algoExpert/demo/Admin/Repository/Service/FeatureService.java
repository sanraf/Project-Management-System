package com.algoExpert.demo.Admin.Repository.Service;

import com.algoExpert.demo.Admin.Entity.FeatureUsage;
import com.algoExpert.demo.Admin.AdminEnums.FeatureType;
import org.springframework.stereotype.Service;

@Service
public interface FeatureService {

    FeatureUsage saveFeatureCount(FeatureUsage featureUsage);
    FeatureUsage updateFeatureCount(FeatureType featureType);
    FeatureUsage findByFeatureType(FeatureType featureType);

    long getRequestCount(FeatureType featureType);

}
