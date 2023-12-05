package com.betrybe.agrix.controller.dto;

import com.betrybe.agrix.model.entities.FarmModel;

/**
 * DTO Farm.
 */
public record FarmDto(Long id, String name, Double size) {

  public FarmModel toFarm() {
    return new FarmModel(id, name, size, null);
  }
  
}
