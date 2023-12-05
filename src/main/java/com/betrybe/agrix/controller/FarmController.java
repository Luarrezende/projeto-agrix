package com.betrybe.agrix.controller;

import com.betrybe.agrix.controller.dto.CropDto;
import com.betrybe.agrix.controller.dto.FarmDto;
import com.betrybe.agrix.model.entities.CropModel;
import com.betrybe.agrix.model.entities.FarmModel;
import com.betrybe.agrix.service.FarmService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller Farm.
 */
@RestController
@RequestMapping("/farms")
public class FarmController {
  private final FarmService farmService;
  
  @Autowired
  public FarmController(FarmService farmService) {
    this.farmService = farmService;
  }

  /**
    * Create Farm.
    */
  @PostMapping()
  public ResponseEntity<FarmModel> insertFarm(@RequestBody FarmDto farmDto) {
    FarmModel newFarm = farmService.insertFarm(farmDto.toFarm());
    return ResponseEntity.status(HttpStatus.CREATED).body(newFarm);
  }

  @GetMapping
  public ResponseEntity<List<FarmModel>> getAll() {
    List<FarmModel> farms = farmService.getAll();
    return ResponseEntity.status(HttpStatus.OK).body(farms);
  }

  /**
    * get farm with id.
    */
  @GetMapping("/{id}")
  public ResponseEntity<FarmDto> getById(@PathVariable Long id) {
    FarmModel farm = farmService.getById(id);
    FarmDto farmDto = new FarmDto(farm.getId(), farm.getName(), farm.getSize());
    return ResponseEntity.status(HttpStatus.OK).body(farmDto);
  }

  /**
   * cria Crop controller.
   */
  @PostMapping("/{farmId}/crops")
  public ResponseEntity<CropDto> createCrop(@PathVariable Long farmId,
      @RequestBody CropDto cropDto) {
    CropModel cropModel = farmService.createCrop(farmId, cropDto.toCrop());
    
    CropDto crop = CropDto.fromCropModel(cropModel, farmId);
    return ResponseEntity.status(HttpStatus.CREATED).body(crop);
  }

  /**
   * pega Crop controller.
   */
  @GetMapping("/{farmId}/crops")
  public ResponseEntity<List<CropDto>> getFarmCrops(@PathVariable Long farmId) {
    List<CropModel> crops = farmService.getFarmCrops(farmId);
    List<CropDto> cropsDto = crops.stream()
        .map(crop -> new CropDto(crop.getId(), crop.getName(), crop.getPlantedArea(),
             crop.getPlantedDate(), crop.getHarvestDate(), farmId)).toList();

    return ResponseEntity.status(HttpStatus.OK).body(cropsDto);
  }
}
