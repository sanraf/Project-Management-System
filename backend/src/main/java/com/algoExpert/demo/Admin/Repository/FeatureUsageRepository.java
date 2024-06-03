package com.algoExpert.demo.Admin.Repository;

import com.algoExpert.demo.Admin.Entity.FeatureUsage;
import com.algoExpert.demo.Admin.AdminEnums.FeatureType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface FeatureUsageRepository extends JpaRepository<FeatureUsage,Integer> {

    FeatureUsage  findByFeatureType(FeatureType featureType);
}
