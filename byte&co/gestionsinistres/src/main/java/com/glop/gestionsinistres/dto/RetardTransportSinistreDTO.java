package com.glop.gestionsinistres.dto;

public class RetardTransportSinistreDTO extends SinistreDTO {

    private String moyenTransport;
    private int dureeRetardMinutes;

    public RetardTransportSinistreDTO() {
        super();
    }

    public String getMoyenTransport() {
        return this.moyenTransport;
    }

    public void setMoyenTransport(String moyenTransport) {
        this.moyenTransport = moyenTransport;
    }

    public int getDureeRetardMinutes() {
        return this.dureeRetardMinutes;
    }

    public void setDureeRetardMinutes(int dureeRetardMinutes) {
        this.dureeRetardMinutes = dureeRetardMinutes;
    }

}
