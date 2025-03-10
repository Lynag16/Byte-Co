package com.glop.gestionoffre;

import jakarta.persistence.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "offres")
public class Offre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idoffre;

    private String nomoffre;

    private double prixoffre;

    private String descriptionoffre;

    private String conditionseligibilite;

    // Getters and Setters
    public Long getIdoffre() {
        return idoffre;
    }

    public void setIdoffre(Long idoffre) {
        this.idoffre = idoffre;
    }

    public String getNomoffre() {
        return nomoffre;
    }

    public void setNomoffre(String nomoffre) {
        this.nomoffre = nomoffre;
    }

    public double getPrixoffre() {
        return prixoffre;
    }

    public void setPrixoffre(double prixoffre) {
        this.prixoffre = prixoffre;
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
}
