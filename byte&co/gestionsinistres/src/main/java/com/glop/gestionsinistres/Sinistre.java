package com.glop.gestionsinistres;

import jakarta.persistence.*;
import java.util.Date;

@Entity
public class Sinistre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private String type;
    private Date dateDeclaration;
    private double montantEstime;
    private String statut;



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

    public String getType() {
        return type;
    }

    public void setType(String type) {
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

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    
}
