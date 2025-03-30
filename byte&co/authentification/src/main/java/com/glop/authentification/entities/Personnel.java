package com.glop.authentification.entities;

import jakarta.persistence.Column;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Personnel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idpersonnel;

    private String nompersonnel;
    private String prenompersonnel;
	@Column(unique = true)

    private String emailpersonnel;
    private String telephonepersonnel;
    private String rolepersonnel;
    private String departementpersonnel;
    private String adressepersonnel;
    private String motdepassepersonnel;

	public Personnel() {}

	public Personnel(String nompersonnel, String prenompersonnel, String emailpersonnel, String telephonepersonnel, String rolepersonnel, String departementpersonnel, String adressepersonnel, String motdepassepersonnel) {
		this.nompersonnel = nompersonnel;
		this.prenompersonnel = prenompersonnel;
		this.emailpersonnel = emailpersonnel;
		this.telephonepersonnel = telephonepersonnel;
		this.rolepersonnel = rolepersonnel;
		this.departementpersonnel = departementpersonnel;
		this.adressepersonnel = adressepersonnel;
		this.motdepassepersonnel = motdepassepersonnel;
	}

	public Long getIdpersonnel() {
		return idpersonnel;
	}
	public void setIdpersonnel(Long idpersonnel) {
		this.idpersonnel = idpersonnel;
	}
	public String getNompersonnel() {
		return nompersonnel;
	}
	public void setNompersonnel(String nompersonnel) {
		this.nompersonnel = nompersonnel;
	}
	public String getPrenompersonnel() {
		return prenompersonnel;
	}
	public void setPrenompersonnel(String prenompersonnel) {
		this.prenompersonnel = prenompersonnel;
	}
	public String getEmailpersonnel() {
		return emailpersonnel;
	}
	public void setEmailpersonnel(String emailpersonnel) {
		this.emailpersonnel = emailpersonnel;
	}
	public String getTelephonepersonnel() {
		return telephonepersonnel;
	}
	public void setTelephonepersonnel(String telephonepersonnel) {this.telephonepersonnel = telephonepersonnel;}
	public String getRolepersonnel() {
		return rolepersonnel;
	}
	public void setRolepersonnel(String rolepersonnel) {
		this.rolepersonnel = rolepersonnel;
	}
	public String getDepartementpersonnel() {
		return departementpersonnel;
	}
	public void setDepartementpersonnel(String departementpersonnel) {
		this.departementpersonnel = departementpersonnel;
	}
	public String getAdressepersonnel() {
		return adressepersonnel;
	}
	public void setAdressepersonnel(String adressepersonnel) {
		this.adressepersonnel = adressepersonnel;
	}
	public String setMotdepassepersonnel() {
		return motdepassepersonnel;
	}
	public void setMotdepassepersonnel(String motdepassepersonnel) {
		this.motdepassepersonnel = motdepassepersonnel;
	}

}
