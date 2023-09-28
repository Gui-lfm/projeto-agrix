package com.betrybe.agrix.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;

/**
 * Entidade de fazenda.
 */
@Entity
@Table(name = "Farms")
public class Farm {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  private Double size;
  @OneToMany(mappedBy = "farm", cascade = CascadeType.ALL)
  @JsonIgnore
  private List<Crop> crops;

  /**
   * Construtor vazio.
   */
  public Farm() {
  }

  /**
   * Construtor de fazenda.
   */
  public Farm(Long id, String name, Double size, List<Crop> crops) {
    this.id = id;
    this.name = name;
    this.size = size;
    this.crops = crops;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Double getSize() {
    return size;
  }

  public void setSize(Double size) {
    this.size = size;
  }

  public List<Crop> getCrops() {
    return crops;
  }

  public void setCrops(List<Crop> crops) {
    this.crops = crops;
  }
}
