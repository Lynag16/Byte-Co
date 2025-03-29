package com.glop.gestionsinistres.dto;

import org.springframework.web.multipart.MultipartFile;

public class AccidentRouteSinistreDTO extends SinistreDTO {

    private String lieuAccident;
    private MultipartFile constat;
    private String immatriculation;

    public AccidentRouteSinistreDTO() {
        super();
    }

    public String getLieuAccident() { return lieuAccident; }
    public void setLieuAccident(String lieuAccident) { this.lieuAccident = lieuAccident; }

    public MultipartFile getConstat() { return constat; }
    public void setConstat(MultipartFile constat) { this.constat = constat; }

    public String getImmatriculation() { return immatriculation; }
    public void setImmatriculation(String immatriculation) { this.immatriculation = immatriculation; }
}
