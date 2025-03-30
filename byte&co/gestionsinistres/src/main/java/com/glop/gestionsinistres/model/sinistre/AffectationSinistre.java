package com.glop.gestionsinistres.model.sinistre;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.time.LocalDate;

@Entity
public class AffectationSinistre {

    @Id
    @GeneratedValue
    private Long id;

    private Long sinistreId;
    private String partenaireId;
    private LocalDate dateAffectation;
    private String statut;

    public AffectationSinistre() {
    }

    public AffectationSinistre(Long sinistreId, String partenaireId, LocalDate dateAffectation, String statut) {
        this.sinistreId = sinistreId;
        this.partenaireId = partenaireId;
        this.dateAffectation = dateAffectation;
        this.statut = statut;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSinistreId() {
        return sinistreId;
    }

    public void setSinistreId(Long sinistreId) {
        this.sinistreId = sinistreId;
    }

    public String getPartenaireId() {
        return partenaireId;
    }

    public void setPartenaireId(String partenaireId) {
        this.partenaireId = partenaireId;
    }

    public LocalDate getDateAffectation() {
        return dateAffectation;
    }

    public void setDateAffectation(LocalDate dateAffectation) {
        this.dateAffectation = dateAffectation;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    @Override
    public String toString() {
        return "AffectationAssistance{" +
                "id=" + id +
                ", sinistreId=" + sinistreId +
                ", partenaireId='" + partenaireId + '\'' +
                ", dateAffectation=" + dateAffectation +
                ", statut='" + statut + '\'' +
                '}';
    }

}

