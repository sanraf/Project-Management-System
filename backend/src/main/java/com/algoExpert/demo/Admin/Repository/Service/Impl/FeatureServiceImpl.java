package com.algoExpert.demo.Admin.Repository.Service.Impl;

import com.algoExpert.demo.Admin.Entity.FeatureUsage;
import com.algoExpert.demo.Admin.AdminEnums.FeatureType;
import com.algoExpert.demo.Admin.Repository.FeatureUsageRepository;
import com.algoExpert.demo.Admin.Repository.Service.FeatureService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;


/**
 * FeatureServiceImpl class implements FeatureService for managing feature usage counts each time
 * a feature API is requested.
 * This service provides methods to increment and retrieve usage counts for different features,
 * as well as updating the counts in the database.
 * @Author Santos Rafaelo
 */
@Service
@Slf4j
public class FeatureServiceImpl implements FeatureService {

    /**
     * A ConcurrentHashMap to store feature types and their corresponding usage counts.
     * The usage counts are stored as AtomicLong to ensure thread safety.
     */
    private final ConcurrentHashMap<FeatureType, AtomicLong> requestCountMap = new ConcurrentHashMap<>();
    private final FeatureUsageRepository featureUsageRepository;

    /**
     * Constructs a FeatureUsageService with the specified FeatureUsageRepository.
     * Initializes the request counts for all feature types.
     * @param featureUsageRepository The repository for accessing and updating feature usage counts in the database
     */
    public FeatureServiceImpl(FeatureUsageRepository featureUsageRepository) {
        this.featureUsageRepository = featureUsageRepository;
        this.initializeRequestCounts();
    }


    /**
     * Initializes the request counts for all feature types.
     * Retrieves the counts from the database and populates the requestCountMap.
     * If a count is not found in the database, it is initialized to 0.
     */
    private void initializeRequestCounts() {

        for (FeatureType featureType : FeatureType.values()) {
            FeatureUsage featureUsage = findByFeatureType(featureType);
            long count = (featureUsage != null) ? featureUsage.getUsageCount() : 0L;
            requestCountMap.put(featureType, new AtomicLong(count));
        }
    }

    /**
     * Increments the usage count for the specified feature type.
     *
     * @param featureType The feature type for which to increment the usage count.
     */
    public long incrementRequestCount(FeatureType featureType) {
        return requestCountMap.get(featureType).incrementAndGet();
    }

    /**
     * Retrieves the current usage count for the specified feature type.
     *
     * @param featureType The feature type for which to retrieve the usage count.
     * @return The current usage count for the specified feature type.
     */
    @Override
    public long getRequestCount(FeatureType featureType) {
        return requestCountMap.get(featureType).get();
    }


    /**
     * <p>
     * Updates the usage count for the specified feature type.
     * </p>
     * <p>
     * Retrieves the current count, increments it, updates the database, and returns the updated FeatureUsage object.
     * If the feature type does not exist in the database, a new FeatureUsage object is created and saved.
     * </p>
     *
     * @param featureType The feature type for which to update the usage count.
     * @return The updated FeatureUsage object after updating the count in the database.
     */
    public FeatureUsage updateFeatureCount(FeatureType featureType) {


        FeatureUsage featureUsage = findByFeatureType(featureType);
        long count = incrementRequestCount(featureType);
        if (featureUsage != null) {
            featureUsage.setUsageCount(count);
            return saveFeatureCount(featureUsage);
        } else {
            FeatureUsage newFeatureUsage = FeatureUsage.builder()
                    .usageCount(count)
                    .featureType(featureType)
                    .build();
            return saveFeatureCount(newFeatureUsage);
        }
    }

    public FeatureUsage findByFeatureType(FeatureType featureType) {
        return featureUsageRepository.findByFeatureType(featureType);
    }

    /**
     *
     * Saves the given FeatureUsage object in the database.
     *
     * @param featureUsage The FeatureUsage object to be saved.
     * @return The saved FeatureUsage object.
     */
    public FeatureUsage saveFeatureCount(FeatureUsage featureUsage) {
        return featureUsageRepository.save(featureUsage);
    }


}
