package com.betrybe.agrix.controller.dto;

import java.time.LocalDate;

/**
 * Crop DTO.
 */
public record CropDtos(Long id, String name, Double plantedArea, 
    LocalDate plantedDate, LocalDate harvestDate, Long farmId) {    
}
