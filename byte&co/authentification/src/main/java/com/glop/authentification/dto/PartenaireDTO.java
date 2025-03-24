package com.glop.authentification.dto;

public class PartenaireDTO {
    private int idPartenaire;
    private String nomPartenaire;
    private String prenomPartenaire;
    private String emailPartenaire;
    private String telephonePartenaire;
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
    public String getPrenomPartenaire() {
        return prenomPartenaire;
    }
    public void setPrenomPartenaire(String prenomPartenaire) {
        this.prenomPartenaire = prenomPartenaire;
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
