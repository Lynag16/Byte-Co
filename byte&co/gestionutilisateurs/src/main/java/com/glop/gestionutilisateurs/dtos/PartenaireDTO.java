package com.glop.gestionutilisateurs.dtos;

public class PartenaireDTO {
    private int idPartenaire;
    private String nomPartenaire;
    private String zonegeo;
    private String emailPartenaire;
    private String telephonePartenaire;
    private String motdepassePartenaire;  // Add this field if it's missing
    private String typeService;
    private String adressePartenaire;

    // Getters and Setters

    public int getIdPartenaire() {
        return idPartenaire;
    }

    public void setIdPartenaire(int idPartenaire) {
        this.idPartenaire = idPartenaire;
    }

    public String getNomPartenaire() {
        return nomPartenaire;
    }

    public void setNomPartenaire(String nomPartenaire) {
        this.nomPartenaire = nomPartenaire;
    }

    public String getzonegeo() {
        return zonegeo;
    }

    public void setzonegeo(String zonegeo) {
        this.zonegeo = zonegeo;
    }

    public String getEmailPartenaire() {
        return emailPartenaire;
    }

    public void setEmailPartenaire(String emailPartenaire) {
        this.emailPartenaire = emailPartenaire;
    }

    public String getTelephonePartenaire() {
        return telephonePartenaire;
    }

    public void setTelephonePartenaire(String telephonePartenaire) {
        this.telephonePartenaire = telephonePartenaire;
    }

    public String getMotdepassePartenaire() {
        return motdepassePartenaire;
    }

    public void setMotdepassePartenaire(String motdepassePartenaire) {
        this.motdepassePartenaire = motdepassePartenaire;
    }

    public String getTypeService() {
        return typeService;
    }

    public void setTypeService(String typeService) {
        this.typeService = typeService;
    }

    public String getAdressePartenaire() {
        return adressePartenaire;
    }

    public void setAdressePartenaire(String adressePartenaire) {
        this.adressePartenaire = adressePartenaire;
    }
}
