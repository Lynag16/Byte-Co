package com.glop.gestionoffre;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;

import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


import java.util.List;

@Entity
public class Offre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomoffre;
    //private double prixoffre;
    private String descriptionoffre;
    private String conditionseligibilite;
    private String imageoffre;
    private List<String> avantagesoffre;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomoffre() {
        return nomoffre;
    }

    public void setNomoffre(String nomoffre) {
        this.nomoffre = nomoffre;
    }


    public String getDescriptionoffre() {
        return descriptionoffre;
    }

    public void setDescriptionoffre(String descriptionoffre) {
        this.descriptionoffre = descriptionoffre;
    }

    public String getConditionseligibilite() {
        return conditionseligibilite;
    }

    public void setConditionseligibilite(String conditionseligibilite) {
        this.conditionseligibilite = conditionseligibilite;
    }

    public String getImageoffre() {
        return imageoffre;
    }

    public void setImageoffre(String imageoffre) {
        this.imageoffre = imageoffre;
    }

    public List<String> getAvantagesoffre() {
        return avantagesoffre;
    }

    public void setAvantagesoffre(List<String> avantagesoffre) {
        this.avantagesoffre = avantagesoffre;
    }

    public Offre() {
    }

    public Offre(Long id, String nomoffre, String descriptionoffre, String conditionseligibilite, String imageoffre, List<String> avantagesoffre) {
        this.id = id;
        this.nomoffre = nomoffre;
        this.descriptionoffre = descriptionoffre;
        this.conditionseligibilite = conditionseligibilite;
        this.imageoffre = imageoffre;
        this.avantagesoffre = avantagesoffre;
}

}