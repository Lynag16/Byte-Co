package com.glop.gestionsinistres.dto;

public class ProblemeHebergementSinistreDTO extends SinistreDTO {

    private String nomHotel;
    private String natureProbleme;

    public String getNomHotel() {
        return nomHotel;
    }
    public void setNomHotel(String nomHotel) {
        this.nomHotel = nomHotel;
    }

    public String getNatureProbleme() {
        return natureProbleme;
    }
    public void setNatureProbleme(String natureProbleme) {
        this.natureProbleme = natureProbleme;
    }
}
