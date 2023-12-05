package com.betrybe.agrix.service;

import com.betrybe.agrix.exceptions.src.main.java.com.betrybe.agrix.exceptions.CropNotFoundException;
import com.betrybe.agrix.exceptions.src.main.java.com.betrybe.agrix.exceptions.FertilizerNotFoundException;
import com.betrybe.agrix.model.entities.CropModel;
import com.betrybe.agrix.model.entities.Fertilizer;
import com.betrybe.agrix.model.repositories.CropRepository;
import com.betrybe.agrix.model.repositories.FertilizerRepository;
import java.time.LocalDate;
import java.util.List;
import org.springframework.stereotype.Service;


/**
 * Crop service.
 */
@Service
public class CropService {
  private final CropRepository cropRepository;
  private final FertilizerRepository fertilizerRepository;

  public CropService(CropRepository cropRepository, FertilizerRepository fertilizerRepository) {
    this.cropRepository = cropRepository;
    this.fertilizerRepository = fertilizerRepository;
  }
  
  public List<CropModel> getAll() {
    return cropRepository.findAll();
  }

  /**
   * Get id crops.
   */
  public CropModel getById(Long id) {
    return cropRepository.findById(id)
            .orElseThrow(CropNotFoundException::new);
  }

  /**
    * pega start e end.
    */
  public List<CropModel> findCropsByHarvestDate(LocalDate start, LocalDate end) {
    return cropRepository.findByHarvestDateBetween(start, end);
  }

  /**
   * This method associates a crop with a fertilizer.
   */
  public void associateCropWithFertilizer(Long cropId, Long fertilizerId) {
    CropModel cropModel = cropRepository.findById(cropId)
          .orElseThrow(CropNotFoundException::new);
    Fertilizer fertilizer = fertilizerRepository.findById(fertilizerId)
          .orElseThrow(FertilizerNotFoundException::new);
    cropModel.getFertilizer().add(fertilizer);
    cropRepository.save(cropModel);
  }

  /**
   * This method gets all fertilizers that belongs to a crop.
   */
  public List<Fertilizer> getFertilizersByCrop(Long cropId) {
    CropModel cropModel = cropRepository.findById(cropId)
         .orElseThrow(CropNotFoundException::new);
    return cropModel.getFertilizer();
  }
}
