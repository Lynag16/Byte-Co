package com.glop.gestionsinistres.dto;

import java.math.BigDecimal;

public class DetailsSinistreDTO {

    private Long id;
    private Long sinistreId;

    private String objetPerdu;
    private String lieuIncident;
    private BigDecimal valeurEstimee;

    private Integer nombreBlesses;
    private String nomHopital;

    private String panneType;
    private String lieuHebergement;

    private Integer retardHeures;
    private String transportConcerne;

    private String objetEndommage;
    private BigDecimal degatsEstimes;

    // === Getters & Setters ===

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSinistreId() {
        return sinistreId;
    }

    public void setSinistreId(Long sinistreId) {
        this.sinistreId = sinistreId;
    }

    public String getObjetPerdu() {
        return objetPerdu;
    }

    public void setObjetPerdu(String objetPerdu) {
        this.objetPerdu = objetPerdu;
    }

    public String getLieuIncident() {
        return lieuIncident;
    }

    public void setLieuIncident(String lieuIncident) {
        this.lieuIncident = lieuIncident;
    }

    public BigDecimal getValeurEstimee() {
        return valeurEstimee;
    }

    public void setValeurEstimee(BigDecimal valeurEstimee) {
        this.valeurEstimee = valeurEstimee;
    }

    public Integer getNombreBlesses() {
        return nombreBlesses;
    }

    public void setNombreBlesses(Integer nombreBlesses) {
        this.nombreBlesses = nombreBlesses;
    }

    public String getNomHopital() {
        return nomHopital;
    }

    public void setNomHopital(String nomHopital) {
        this.nomHopital = nomHopital;
    }

    public String getPanneType() {
        return panneType;
    }

    public void setPanneType(String panneType) {
        this.panneType = panneType;
    }

    public String getLieuHebergement() {
        return lieuHebergement;
    }

    public void setLieuHebergement(String lieuHebergement) {
        this.lieuHebergement = lieuHebergement;
    }

    public Integer getRetardHeures() {
        return retardHeures;
    }

    public void setRetardHeures(Integer retardHeures) {
        this.retardHeures = retardHeures;
    }

    public String getTransportConcerne() {
        return transportConcerne;
    }

    public void setTransportConcerne(String transportConcerne) {
        this.transportConcerne = transportConcerne;
    }

    public String getObjetEndommage() {
        return objetEndommage;
    }

    public void setObjetEndommage(String objetEndommage) {
        this.objetEndommage = objetEndommage;
    }

    public BigDecimal getDegatsEstimes() {
        return degatsEstimes;
    }

    public void setDegatsEstimes(BigDecimal degatsEstimes) {
        this.degatsEstimes = degatsEstimes;
    }

}
