package com.glop.gestionutilisateurs.entities;

import java.util.Date;
import jakarta.persistence.Table;

import jakarta.persistence.Column;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Table(name = "partenaires")
@Entity
public class Partenaire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPartenaire;
    private String nomPartenaire;
    private String zonegeo;


	@Column(unique = true)
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
