package com.glop.gestionsinistres.model;

import jakarta.persistence.Entity;

import static com.glop.gestionsinistres.model.TypeSinistre.RETARD_TRANSPORT;

@Entity
public class RetardTransportSinistre extends Sinistre {

    private String moyenTransport;
    private int dureeRetardMinutes;

    public RetardTransportSinistre() {
        super();
        setType(RETARD_TRANSPORT);
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
