package com.glop.authentification.entities;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idClient;
    private String nom;
    private String prenom;
    private String email;
    private String telephone;
    private String motdepasse; 
    private Date dateInscription;
    private String languePreference;
    private String monnaiePreference;
    private String adresseclient;
    private int badge;
	public int getIdClient() {
		return idClient;
	}
	public void setIdClient(int idClient) {
		this.idClient = idClient;
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
	public int getBadge() {
		return badge;
	}
	public void setBadge(int badge) {
		this.badge = badge;
	}
	public String getAdresseclient() {
		return adresseclient;
	}
	public void setAdresseclient(String adresseclient) {
		this.adresseclient = adresseclient;
	}
    
    
}
