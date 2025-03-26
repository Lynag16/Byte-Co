package com.glop.gestionsinistres.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
public class Sinistre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    @Enumerated(EnumType.STRING)
    private TypeSinistre type;

    @Temporal(TemporalType.DATE)
    private Date dateDeclaration;

    private double montantEstime;

    @Enumerated(EnumType.STRING)
    private StatutSinistre statut;

    private String userId; // car le token contient l'ID comme ça

    @OneToOne(mappedBy = "sinistre", cascade = CascadeType.ALL, orphanRemoval = true)
    private DetailsSinistre details;

    public Sinistre() {
    }

    public Sinistre(String description, TypeSinistre type, Date dateDeclaration, double montantEstime, StatutSinistre statut, String userId) {
        this.description = description;
        this.type = type;
        this.dateDeclaration = dateDeclaration;
        this.montantEstime = montantEstime;
        this.statut = statut;
        this.userId = userId;
    }

    // === Getters & Setters ===

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TypeSinistre getType() {
        return type;
    }

    public void setType(TypeSinistre type) {
        this.type = type;
    }

    public Date getDateDeclaration() {
        return dateDeclaration;
    }

    public void setDateDeclaration(Date dateDeclaration) {
        this.dateDeclaration = dateDeclaration;
    }

    public double getMontantEstime() {
        return montantEstime;
    }

    public void setMontantEstime(double montantEstime) {
        this.montantEstime = montantEstime;
    }

    public StatutSinistre getStatut() {
        return statut;
    }

    public void setStatut(StatutSinistre statut) {
        this.statut = statut;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public DetailsSinistre getDetails() {
        return details;
    }

    public void setDetails(DetailsSinistre details) {
        this.details = details;
    }
}
