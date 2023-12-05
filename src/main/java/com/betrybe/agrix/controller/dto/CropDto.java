package com.betrybe.agrix.controller.dto;

import com.betrybe.agrix.model.entities.CropModel;
import java.time.LocalDate;

/**
 * DTO Crop.
 */
public record CropDto(
        Long id, String name, Double plantedArea, LocalDate plantedDate,
        LocalDate harvestDate, Long farmId) {
  public CropModel toCrop() {
    return new CropModel(id, name, plantedArea, plantedDate, harvestDate);
  }

  /**
   * DTO Crop from model.
   */
  public static CropDto fromCropModel(CropModel cropModel, Long farmId) {
    return new CropDto(
            cropModel.getId(), cropModel.getName(), cropModel.getPlantedArea(),
            cropModel.getPlantedDate(), cropModel.getHarvestDate(),
            farmId
    );
  }
}

