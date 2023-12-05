package com.betrybe.agrix.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import lombok.Data;

/**
 * Model representing a farm.
 */
@Data
@Entity
@Table(name = "farms")
public class FarmModel {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;
  private Double size;

  @OneToMany(mappedBy = "farmModel")
  @JsonIgnore
  private List<CropModel> crops;

  public FarmModel() {
  }

  /**
   * contructor com coisas.
   */
  public FarmModel(Long id, String name, Double size, List<CropModel> crops) {
    this.id = id;
    this.name = name;
    this.size = size;
    this.crops = crops;
  }
}
