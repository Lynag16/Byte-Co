package com.glop.gestionsinistres.model.sinistre;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class AffectationSinistre {

    @Id
    @GeneratedValue
    private Long id;

    private Long sinistreId;
    private String partenaireId;
    private LocalDate dateAffectation;
    @Enumerated(EnumType.STRING)
    private StatutSinistre statut;

    public AffectationSinistre() {
        this.dateAffectation = LocalDate.now();
        this.statut = StatutSinistre.EN_COURS;
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

    public StatutSinistre getStatut() { return this.statut; }

    public void setStatut(StatutSinistre statut) { this.statut = statut; }

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

