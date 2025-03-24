package com.glop.authentification.dto;



public class ClientDTO {
    private int idClient;
    private String nom;
    private String prenom;
    private String email;
    private String telephone;
    private String languePreference;
    private String monnaiePreference;
    private String adresseclient;
    private String badge;
    private String statut;
    private String motdepasse;
    
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
	public String getBadge() {
		return badge;
	}
	public void setBadge(String badge) {
		this.badge = badge;
	}
	public String getStatut() {
		return statut;
	}
	public void setStatut(String statut) {
		this.statut = statut;
	}
	public String getMotdepasse() {
		return motdepasse;
	}
	public void setMotdepasse(String motdepasse) {
		this.motdepasse = motdepasse;
	}  
	
	
    
}
