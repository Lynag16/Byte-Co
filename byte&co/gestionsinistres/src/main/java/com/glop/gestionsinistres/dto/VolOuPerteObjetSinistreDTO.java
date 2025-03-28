package com.glop.gestionsinistres.dto;

import org.springframework.web.multipart.MultipartFile;

public class VolOuPerteObjetSinistreDTO extends SinistreDTO {

    private String lieuVol;
    private String descriptionObjetPerdu;
    private double valeurObjetPerdu;
    private MultipartFile declarationPolice;

    public VolOuPerteObjetSinistreDTO() {
        super();
    }

    public String getLieuVol() { return lieuVol; }
    public void setLieuVol(String lieuVol) { this.lieuVol = lieuVol; }

    public String getDescriptionObjetPerdu() { return descriptionObjetPerdu; }
    public void setDescriptionObjetPerdu(String descriptionObjetPerdu) { this.descriptionObjetPerdu = descriptionObjetPerdu; }

    public double getValeurObjetPerdu() { return valeurObjetPerdu; }
    public void setValeurObjetPerdu(double valeurObjetPerdu) { this.valeurObjetPerdu = valeurObjetPerdu; }

    public MultipartFile getDeclarationPolice() { return declarationPolice; }
    public void setDeclarationPolice(MultipartFile declarationPoliceFilePath) { this.declarationPolice = declarationPoliceFilePath; }
}
