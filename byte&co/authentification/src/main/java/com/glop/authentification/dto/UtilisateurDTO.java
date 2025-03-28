package com.glop.authentification.dto;

public class UtilisateurDTO {
    private Long idUtilisateur;
    private String email;
    private String motDePasse;
    private String typeUtilisateur;
    private String nom;
    private String prenom;
    private String dateNaissance;
    private String codePostal;

    // Constructeurs
    public UtilisateurDTO() {}

    public UtilisateurDTO(String email, String motDePasse, String typeUtilisateur, String nom, String prenom, String dateNaissance, String codePostal) {
        this.email = email;
        this.motDePasse = motDePasse;
        this.typeUtilisateur = typeUtilisateur;
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.codePostal = codePostal;
    }

    // Getters et Setters
    public Long getId() {
        return idUtilisateur;
    }

    public void setId(Long idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }
    
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

    public String getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(String dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }
}