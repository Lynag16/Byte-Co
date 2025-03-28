package com.glop.gestionsinistres.model;

import jakarta.persistence.Entity;

@Entity
public class DommagesMaterielsSinistre extends Sinistre {

    private String typeMateriel;
    private double estimationDegats;

    public DommagesMaterielsSinistre() {
        super();
    }

    public String getTypeMateriel() {
        return typeMateriel;
    }

    public void setTypeMateriel(String typeMateriel) {
        this.typeMateriel = typeMateriel;
    }

    public double getEstimationDegats() {
        return estimationDegats;
    }

    public void setEstimationDegats(double estimationDegats) {
        this.estimationDegats = estimationDegats;
    }
}
