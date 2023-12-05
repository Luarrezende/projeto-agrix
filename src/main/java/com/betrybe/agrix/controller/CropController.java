package com.betrybe.agrix.controller;

import com.betrybe.agrix.controller.dto.CropDto;
import com.betrybe.agrix.controller.dto.CropDtos;
import com.betrybe.agrix.model.entities.CropModel;
import com.betrybe.agrix.model.entities.Fertilizer;
import com.betrybe.agrix.service.CropService;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * Crop controller.
 */
@RestController
@RequestMapping("/crops")
public class CropController {
  private final CropService cropService;

  @Autowired
  public CropController(CropService cropService) {
    this.cropService = cropService;
  }

  /**
  * Get all crops.
  */
  @GetMapping
  public ResponseEntity<List<CropDtos>> getAllCrop() {
    List<CropModel> crops = cropService.getAll();
    List<CropDtos> cropsDto = crops.stream()
        .map(crop -> 
            new CropDtos(
                crop.getId(), crop.getName(), crop.getPlantedArea(),
                  crop.getPlantedDate(), crop.getHarvestDate(),
                    crop.getFarmModel().getId()))
                    .toList();
    return ResponseEntity.status(HttpStatus.OK).body(cropsDto);
  }

  /**
  * Get id crops.
  */
  @GetMapping("/{id}")
  public ResponseEntity<CropDto> getById(@PathVariable Long id) {
    CropModel crops = cropService.getById(id);

    return ResponseEntity.status(HttpStatus.OK).body(CropDto.fromCropModel(crops, id));
  }

  /**
   * pega start e end.
   */
  @GetMapping("/search")
  public ResponseEntity<List<CropDtos>> findCropsByHarvestDate(@RequestParam LocalDate start,
      @RequestParam LocalDate end) {
    List<CropModel> crops = cropService.findCropsByHarvestDate(start, end);
    List<CropDtos> cropsDto = crops.stream()
        .map(crop -> 
            new CropDtos(
                crop.getId(), crop.getName(), crop.getPlantedArea(),
                  crop.getPlantedDate(), crop.getHarvestDate(),
                    crop.getFarmModel().getId()))
                   .toList();
    return ResponseEntity.status(HttpStatus.OK).body(cropsDto);
  }

  /**
   * This method associates a crop with a fertilizer.
   */
  @PostMapping("/{cropId}/fertilizers/{fertilizerId}")
  public ResponseEntity<String> associateCropWithFertilizer(
      @PathVariable Long cropId, @PathVariable Long fertilizerId
  ) {
    cropService.associateCropWithFertilizer(cropId, fertilizerId);
    return ResponseEntity.status(HttpStatus.CREATED)
    .body("Fertilizante e plantação associados com sucesso!");
  }

  /**
   * This method gets all fertilizers that belongs to a crop.
   */
  @GetMapping("/{cropId}/fertilizers")
  public ResponseEntity<List<Fertilizer>> getFertilizersByCrop(@PathVariable Long cropId) {
    List<Fertilizer> fertilizers = cropService.getFertilizersByCrop(cropId);
    return ResponseEntity.status(HttpStatus.OK).body(fertilizers);
  }
}
