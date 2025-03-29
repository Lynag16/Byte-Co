package com.glop.gestionsinistres.model;

import jakarta.persistence.Entity;

import static com.glop.gestionsinistres.model.TypeSinistre.INCIDENT_MEDICAL;

@Entity
public class IncidentMedicalSinistre extends Sinistre {

    private String symptomes;
    private Double coutIntervention;
    private String typeIntervention;
    private String dossierMedicalFilePath;

    public IncidentMedicalSinistre() {
        super();
        setType(INCIDENT_MEDICAL);
    }

    public String getSymptomes() {
        return this.symptomes;
    }

    public void setSymptomes(String symptomes) {
        this.symptomes = symptomes;
    }

    public Double getCoutIntervention() { return coutIntervention; }
    public void setCoutIntervention(Double coutIntervention) { this.coutIntervention = coutIntervention; }

    public String getTypeIntervention() { return typeIntervention; }
    public void setTypeIntervention(String typeIntervention) { this.typeIntervention = typeIntervention; }

    public String getDossierMedicalFilePath() { return dossierMedicalFilePath; }
    public void setDossierMedicalFilePath(String dossierMedicalFilePath) { this.dossierMedicalFilePath = dossierMedicalFilePath; }

}
