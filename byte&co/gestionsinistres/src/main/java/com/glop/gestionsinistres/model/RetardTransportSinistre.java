package com.glop.gestionsinistres.model;

import jakarta.persistence.Entity;

@Entity
public class RetardTransportSinistre extends Sinistre {

    private String moyenTransport;
    private int dureeRetardMinutes;

    public RetardTransportSinistre() {
        super();
    }

    public String getMoyenTransport() {
        return moyenTransport;
    }

    public void setMoyenTransport(String moyenTransport) {
        this.moyenTransport = moyenTransport;
    }

    public int getDureeRetardMinutes() {
        return dureeRetardMinutes;
    }

    public void setDureeRetardMinutes(int dureeRetardMinutes) {
        this.dureeRetardMinutes = dureeRetardMinutes;
    }
}
