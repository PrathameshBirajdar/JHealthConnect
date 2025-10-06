// RecommendationService.java
package com.jhealthconnect.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jhealthconnect.entity.Recommendation;
import com.jhealthconnect.repository.RecommendationRepository;

@Service
public class RecommendationService {
    
    @Autowired
    private RecommendationRepository recommendationRepository;
    
    public List<Recommendation> getAllRecommendations() {
        return recommendationRepository.findAll();
    }
    
    public Recommendation getRecommendationById(Long id) {
        return recommendationRepository.findById(id).orElse(null);
    }
    
    public Recommendation saveRecommendation(Recommendation recommendation) {
        return recommendationRepository.save(recommendation);
    }
    
    public void deleteRecommendation(Long id) {
        recommendationRepository.deleteById(id);
    }
}

