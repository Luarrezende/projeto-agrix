package com.betrybe.agrix.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.util.List;
import lombok.Data;

/**
 * Entity fertilizer.
 */
@Data
@Entity
@Table(name = "fertilizer")
public class Fertilizer {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  private String brand;
  private String composition;

  @ManyToMany(mappedBy = "fertilizer")
  @JsonIgnore
  private List<CropModel> cropModel;

  public Fertilizer() {
  }

  /**
   * Constructor.
   */
  public Fertilizer(Long id, String name, String brand,
      String composition, List<CropModel> cropModel) {
    this.id = id;
    this.name = name;
    this.brand = brand;
    this.composition = composition;
    this.cropModel = cropModel;
  }
  
}
