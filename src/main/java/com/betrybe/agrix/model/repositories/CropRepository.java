package com.betrybe.agrix.model.repositories;

import com.betrybe.agrix.model.entities.CropModel;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository Crop.
 */
@Repository
public interface CropRepository extends JpaRepository<CropModel, Long> {
  List<CropModel> findByHarvestDateBetween(LocalDate start, LocalDate end);
}
