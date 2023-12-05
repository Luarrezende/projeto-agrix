package com.betrybe.agrix.controller;

import com.betrybe.agrix.controller.dto.FertilizerDto;
import com.betrybe.agrix.model.entities.Fertilizer;
import com.betrybe.agrix.service.FertilizerService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller Fertilizer.
 */
@RestController
@RequestMapping("/fertilizers")
public class FertilizerController {
  private final FertilizerService fertilizerService;

  public FertilizerController(FertilizerService fertilizerService) {
    this.fertilizerService = fertilizerService;
  }

  @PostMapping
  public ResponseEntity<Fertilizer> insertFertilizer(@RequestBody FertilizerDto fertilizerDto) {
    Fertilizer fertilizer = fertilizerService.create(fertilizerDto.toEntity());
    return ResponseEntity.status(HttpStatus.CREATED).body(fertilizer);
  }

  /**
  * pega tudo.
  */
  @GetMapping
  public ResponseEntity<List<FertilizerDto>> getAllFertilizer() {
    List<Fertilizer> fertilizers = fertilizerService.getAll();
    List<FertilizerDto> fertilizerDto = fertilizers.stream()
        .map(fertilizer -> 
            new FertilizerDto(
                fertilizer.getId(), fertilizer.getName(), fertilizer.getBrand(),
                  fertilizer.getComposition()))
                   .toList();
    return ResponseEntity.status(HttpStatus.OK).body(fertilizerDto);
  }

  /**
  * pega por id.
  */
  @GetMapping("/{id}")
  public ResponseEntity<FertilizerDto> getFertilizerById(@PathVariable Long id) {
    Fertilizer fertilizer = fertilizerService.getById(id);
    FertilizerDto fertilizerDto = new FertilizerDto(
        fertilizer.getId(), fertilizer.getName(), fertilizer.getBrand(),
          fertilizer.getComposition());
    return ResponseEntity.status(HttpStatus.OK).body(fertilizerDto);
  }
}
