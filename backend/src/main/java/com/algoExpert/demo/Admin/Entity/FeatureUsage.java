package com.algoExpert.demo.Admin.Entity;

import com.algoExpert.demo.Admin.AdminEnums.FeatureType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FeatureUsage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)

    private FeatureType featureType;

    private Long usageCount;
}
