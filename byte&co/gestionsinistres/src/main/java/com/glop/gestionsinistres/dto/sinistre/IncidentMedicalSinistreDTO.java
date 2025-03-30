package com.glop.gestionsinistres.dto.sinistre;

import org.springframework.web.multipart.MultipartFile;

public class IncidentMedicalSinistreDTO extends SinistreDTO {

    private String symptomes;
    private Double coutIntervention;
    private MultipartFile dossierMedical;
    private String typeIntervention;

    public String getSymptomes() { return this.symptomes; }
    public void setSymptomes(String symptomes) { this.symptomes = symptomes; }

    public Double getCoutIntervention() { return this.coutIntervention; }
    public void setCoutIntervention(Double coutIntervention) { this.coutIntervention = coutIntervention; }

    public MultipartFile getDossierMedical() { return dossierMedical; }
    public void setDossierMedical(MultipartFile dossierMedical) { this.dossierMedical = dossierMedical; }

    public String getTypeIntervention() { return this.typeIntervention; }
    public void setTypeIntervention(String typeIntervention) { this.typeIntervention = typeIntervention; }
}
