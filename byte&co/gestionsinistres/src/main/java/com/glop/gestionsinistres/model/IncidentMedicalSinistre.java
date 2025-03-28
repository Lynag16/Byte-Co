package com.glop.gestionsinistres.model;

import jakarta.persistence.Entity;

@Entity
public class IncidentMedicalSinistre extends Sinistre {

    private String symptomes;
    private boolean hospitalisation;

    public IncidentMedicalSinistre() {
        super();
    }

    public String getSymptomes() {
        return symptomes;
    }

    public void setSymptomes(String symptomes) {
        this.symptomes = symptomes;
    }

    public boolean isHospitalisation() {
        return hospitalisation;
    }

    public void setHospitalisation(boolean hospitalisation) {
        this.hospitalisation = hospitalisation;
    }
}
