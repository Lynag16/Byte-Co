package com.glop.authentification.dto;

public class UtilisateurDTO {
    private String email;
    private String motDePasse;
    private String typeUtilisateur;

    // Constructeurs
    public UtilisateurDTO() {}

    public UtilisateurDTO(String email, String motDePasse, String typeUtilisateur) {
        this.email = email;
        this.motDePasse = motDePasse;
        this.typeUtilisateur = typeUtilisateur;
    }

    // Getters et Setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public String getTypeUtilisateur() {
        return typeUtilisateur;
    }

    public void setTypeUtilisateur(String typeUtilisateur) {
        this.typeUtilisateur = typeUtilisateur;
    }
}