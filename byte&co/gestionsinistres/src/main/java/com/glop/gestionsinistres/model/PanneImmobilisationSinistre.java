package com.glop.gestionsinistres.model;

import jakarta.persistence.Entity;

@Entity
public class PanneImmobilisationSinistre extends Sinistre {

    private String naturePanne;
    private boolean besoinDepannage;

    public PanneImmobilisationSinistre() {
        super();
        setType(TypeSinistre.PANNE_IMMOBILISATION);
    }

    public String getNaturePanne() {return naturePanne; }
    public void setNaturePanne(String naturePanne) { this.naturePanne = naturePanne;}

    public boolean isBesoinDepannage() { return besoinDepannage; }

    public void setBesoinDepannage(boolean besoinDepannage) { this.besoinDepannage = besoinDepannage; }
}
