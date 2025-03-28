package com.glop.gestionsinistres.dto;

public class PanneImmobilisationSinistreDTO extends SinistreDTO {

    private String naturePanne;
    private boolean besoinDepannage;

    public PanneImmobilisationSinistreDTO() {
        super();
    }

    public String getNaturePanne() {
        return naturePanne;
    }

    public void setNaturePanne(String naturePanne) {
        this.naturePanne = naturePanne;
    }

    public boolean isBesoinDepannage() {
        return besoinDepannage;
    }

    public void setBesoinDepannage(boolean besoinDepannage) {
        this.besoinDepannage = besoinDepannage;
    }
}
