package com.glop.authentification.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Utilisateur")
public class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(name = "motdepasse", nullable = false)
    private String motDePasse;

    @Column(nullable = false)
    private String typeUtilisateur = "USER";

    private String nom;
    private String prenom;
    private String dateNaissance;
    private String codePostal;

    @Column(name = "is_admin", nullable = false)
    private boolean isAdmin;


    public Utilisateur() {}

    public Utilisateur(String email, String motDePasse, String typeUtilisateur, String nom, String prenom, String dateNaissance, String codePostal) {
        this.email = email;
        this.motDePasse = motDePasse;
        this.typeUtilisateur = typeUtilisateur;
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.codePostal = codePostal;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getMotDePasse() { return motDePasse; }
    public void setMotDePasse(String motDePasse) { this.motDePasse = motDePasse; }

    public String getTypeUtilisateur() { return typeUtilisateur; }
    public void setTypeUtilisateur(String typeUtilisateur) { this.typeUtilisateur = typeUtilisateur; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }

    public String getDateNaissance() { return dateNaissance; }
    public void setDateNaissance(String dateNaissance) { this.dateNaissance = dateNaissance; }

    public String getCodePostal() { return codePostal; }
    public void setCodePostal(String codePostal) { this.codePostal = codePostal; }

    public Boolean getIsAdmin() {
    return isAdmin;
    }

    public void setIsAdmin(Boolean isAdmin) {
        this.isAdmin = isAdmin;
    }


}

