package com.glop.authentification.entities;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Partenaire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPartenaire;
    private String nomPartenaire;
    private String prenomPartenaire;
    private String emailPartenaire;
    private String telephonePartenaire;
    private String motdepassePartenaire; 
    private String TypeService;
    private String AdressePartenaire;
    
    
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
	public String getMotdepassePartenaire() {
		return motdepassePartenaire;
	}
	public void setMotdepassePartenaire(String motdepassePartenaire) {
		this.motdepassePartenaire = motdepassePartenaire;
	}
	public String getTypeService() {
		return TypeService;
	}
	public void setTypeService(String typeService) {
		TypeService = typeService;
	}
	public String getAdressePartenaire() {
		return AdressePartenaire;
	}
	public void setAdressePartenaire(String adressePartenaire) {
		AdressePartenaire = adressePartenaire;
	}



    
}
