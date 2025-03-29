package com.glop.gestionsinistres.model;

import jakarta.persistence.Entity;

@Entity
public class AccidentRouteSinistre extends Sinistre {

    private String lieuAccident;
    private String constatFilePath;
    private String immatriculation;

    public AccidentRouteSinistre() {
        super();
        setType(TypeSinistre.ACCIDENT_ROUTE);
    }

    public String getLieuAccident() { return lieuAccident; }
    public void setLieuAccident(String lieuAccident) { this.lieuAccident = lieuAccident; }

    public String getConstatFilePath() { return constatFilePath; }
    public void setConstatFilePath(String constatFilePath) { this.constatFilePath = constatFilePath; }

    public String getImmatriculation() { return immatriculation; }
    public void setImmatriculation(String immatriculation) { this.immatriculation = immatriculation; }
}
