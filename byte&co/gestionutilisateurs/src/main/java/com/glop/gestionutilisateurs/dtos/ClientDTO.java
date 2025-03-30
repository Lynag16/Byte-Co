package com.glop.gestionutilisateurs.dtos;

import java.util.Date;

public class ClientDTO {

    private String nom;
    private String prenom;
    private String email;
    private String telephone;
    private String motdepasse;
    private Date dateInscription;
    private String languePreference;
    private String monnaiePreference;
    private String adresseclient;
    //private String badge;
    private String statut;

    // Constructeurs
    public ClientDTO() {}

    public ClientDTO(String nom, String prenom, String email, String telephone, String motdepasse, Date dateInscription, 
                     String languePreference, String monnaiePreference, String adresseclient, String statut) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.telephone = telephone;
        this.motdepasse = motdepasse;
        this.dateInscription = dateInscription;
        this.languePreference = languePreference;
        this.monnaiePreference = monnaiePreference;
        this.adresseclient = adresseclient;
        //this.badge = badge;
        this.statut = statut;
    }

    // Getters et Setters
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getMotdepasse() {
        return motdepasse;
    }

    public void setMotdepasse(String motdepasse) {
        this.motdepasse = motdepasse;
    }

    public Date getDateInscription() {
        return dateInscription;
    }

    public void setDateInscription(Date dateInscription) {
        this.dateInscription = dateInscription;
    }

    public String getLanguePreference() {
        return languePreference;
    }

    public void setLanguePreference(String languePreference) {
        this.languePreference = languePreference;
    }

    public String getMonnaiePreference() {
        return monnaiePreference;
    }

    public void setMonnaiePreference(String monnaiePreference) {
        this.monnaiePreference = monnaiePreference;
    }

    public String getAdresseclient() {
        return adresseclient;
    }

    public void setAdresseclient(String adresseclient) {
        this.adresseclient = adresseclient;
    }

//    public String getBadge() {
//        return badge;
//    }
//
//    public void setBadge(String badge) {
//        this.badge = badge;
//    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }
}
