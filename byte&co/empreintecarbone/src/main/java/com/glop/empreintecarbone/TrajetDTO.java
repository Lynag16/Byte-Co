package com.glop.empreintecarbone;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.util.Date;

public class TrajetDTO {

    private Long idTrajet;
    private Long idClient;
    private Date dateTrajet;
    private String pointDepart;
    private String pointArrivee;
    private String moyenTransport;
    private Float distance;
    private Float empreinteCarbone;

    // Getters and setters
    public Long getIdTrajet() {
        return idTrajet;
    }

    public void setIdTrajet(Long idTrajet) {
        this.idTrajet = idTrajet;
    }

    public Long getIdClient() {
        return idClient;
    }

    public void setIdClient(Long idClient) {
        this.idClient = idClient;
    }

    public Date getDateTrajet() {
        return dateTrajet;
    }

    public void setDateTrajet(Date dateTrajet) {
        this.dateTrajet = dateTrajet;
    }

    public String getPointDepart() {
        return pointDepart;
    }

    public void setPointDepart(String pointDepart) {
        this.pointDepart = pointDepart;
    }

    public String getPointArrivee() {
        return pointArrivee;
    }

    public void setPointArrivee(String pointArrivee) {
        this.pointArrivee = pointArrivee;
    }

    public String getMoyenTransport() {
        return moyenTransport;
    }

    public void setMoyenTransport(String moyenTransport) {
        this.moyenTransport = moyenTransport;
    }

    public Float getDistance() {
        return distance;
    }

    public void setDistance(Float distance) {
        this.distance = distance;
    }

    public Float getEmpreinteCarbone() {
        return empreinteCarbone;
    }

    public void setEmpreinteCarbone(Float empreinteCarbone) {
        this.empreinteCarbone = empreinteCarbone;
    }
}
