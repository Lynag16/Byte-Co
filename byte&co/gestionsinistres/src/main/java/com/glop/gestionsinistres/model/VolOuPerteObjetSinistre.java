package com.glop.gestionsinistres.model;

import jakarta.persistence.Entity;

@Entity
public class VolOuPerteObjetSinistre extends Sinistre {

    private String lieuVol;
    private String descriptionObjetPerdu;
    private double valeurObjetPerdu;
    private String declarationPoliceFilePath;

    public VolOuPerteObjetSinistre() {
        super();
        setType(TypeSinistre.VOL_OU_PERTE_OBJET);
    }

    public String getLieuVol() { return lieuVol; }
    public void setLieuVol(String lieuVol) { this.lieuVol = lieuVol; }

    public String getDescriptionObjetPerdu() { return descriptionObjetPerdu; }
    public void setDescriptionObjetPerdu(String descriptionObjetPerdu) { this.descriptionObjetPerdu = descriptionObjetPerdu; }

    public double getValeurObjetPerdu() { return valeurObjetPerdu; }
    public void setValeurObjetPerdu(double valeurObjetPerdu) { this.valeurObjetPerdu = valeurObjetPerdu; }

    public String getDeclarationPoliceFilePath() { return declarationPoliceFilePath; }
    public void setDeclarationPoliceFilePath(String declarationPoliceFilePath) { this.declarationPoliceFilePath = declarationPoliceFilePath; }
}
