package com.glop.gestionsinistres.model;

import jakarta.persistence.Entity;

import static com.glop.gestionsinistres.model.TypeSinistre.PROBLEME_HEBERGEMENT;

@Entity
public class ProblemeHebergementSinistre extends Sinistre {

    private String nomHotel;
    private String natureProbleme;

    public ProblemeHebergementSinistre() {
        super();
        setType(PROBLEME_HEBERGEMENT);
    }

    public String getNomHotel() {
        return nomHotel;
    }
    public void setNomHotel(String nomHotel) {
        this.nomHotel = nomHotel;
    }

    public String getNatureProbleme() {
        return natureProbleme;
    }
    public void setNatureProbleme(String natureProbleme) {
        this.natureProbleme = natureProbleme;
    }
}
