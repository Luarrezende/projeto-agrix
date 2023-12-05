package com.betrybe.agrix.service;

import com.betrybe.agrix.exceptions.src.main.java.com.betrybe.agrix.exceptions.FertilizerNotFoundException;
import com.betrybe.agrix.model.entities.Fertilizer;
import com.betrybe.agrix.model.repositories.FertilizerRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
  * service Fer.
  */
@Service
public class FertilizerService {
  FertilizerRepository fertilizerRepository;

  @Autowired
  public FertilizerService(FertilizerRepository fertilizerRepository) {
    this.fertilizerRepository = fertilizerRepository;
  }

  public Fertilizer create(Fertilizer fertilizer) {
    return fertilizerRepository.save(fertilizer);
  }

  public List<Fertilizer> getAll() {
    return fertilizerRepository.findAll();
  }
  
  public Fertilizer getById(Long id) {
    return fertilizerRepository.findById(id)
            .orElseThrow(FertilizerNotFoundException::new);
  }
}
