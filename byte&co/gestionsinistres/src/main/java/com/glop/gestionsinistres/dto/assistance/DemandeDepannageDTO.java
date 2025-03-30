package com.glop.gestionsinistres.dto.assistance;

import com.glop.gestionsinistres.model.assistance.StatutDemande;

import java.time.LocalDate;

public class DemandeDepannageDTO {

    private String userId;
    private String immatriculation;
    private String categorieProbleme;
    private String typeEnergie;
    private String adresse;
    private boolean surAutoroute;
    private LocalDate dateIncident;
    private LocalDate dateSouhaiteeIntervention;
    private String prenomContact;
    private String nomContact;
    private String telephoneContact;
    private StatutDemande statut;
    private LocalDate dateCreation;

    public DemandeDepannageDTO() {
        this.statut = StatutDemande.EN_ATTENTE;
        this.dateCreation = LocalDate.now();
    }

    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getImmatriculation() {
        return immatriculation;
    }
    public void setImmatriculation(String immatriculation) {
        this.immatriculation = immatriculation;
    }

    public String getCategorieProbleme() {
        return categorieProbleme;
    }
    public void setCategorieProbleme(String categorieProbleme) {
        this.categorieProbleme = categorieProbleme;
    }

    public String getTypeEnergie() {
        return typeEnergie;
    }
    public void setTypeEnergie(String typeEnergie) {
        this.typeEnergie = typeEnergie;
    }

    public String getAdresse() {
        return adresse;
    }
    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public boolean isSurAutoroute() {
        return surAutoroute;
    }
    public void setSurAutoroute(boolean surAutoroute) {
        this.surAutoroute = surAutoroute;
    }

    public LocalDate getDateIncident() {
        return dateIncident;
    }
    public void setDateIncident(LocalDate dateIncident) {
        this.dateIncident = dateIncident;
    }

    public LocalDate getDateSouhaiteeIntervention() {
        return dateSouhaiteeIntervention;
    }
    public void setDateSouhaiteeIntervention(LocalDate dateSouhaiteeIntervention) {
        this.dateSouhaiteeIntervention = dateSouhaiteeIntervention;
    }

    public String getPrenomContact() {
        return prenomContact;
    }
    public void setPrenomContact(String prenomContact) {
        this.prenomContact = prenomContact;
    }

    public String getNomContact() {
        return nomContact;
    }
    public void setNomContact(String nomContact) {
        this.nomContact = nomContact;
    }

    public String getTelephoneContact() {
        return telephoneContact;
    }
    public void setTelephoneContact(String telephoneContact) {
        this.telephoneContact = telephoneContact;
    }

    public StatutDemande getStatut() {
        return statut;
    }
    public void setStatut(StatutDemande statut) {
        this.statut = statut;
    }

    public LocalDate getDateCreation() {
        return dateCreation;
    }
    public void setDateCreation(LocalDate dateCreation) {
        this.dateCreation = dateCreation;
    }

    @Override
    public String toString() {
        return "DemandeDepannageDTO{" +
                "userId='" + userId + '\'' +
                ", immatriculation='" + immatriculation + '\'' +
                ", categorieProbleme='" + categorieProbleme + '\'' +
                ", typeEnergie='" + typeEnergie + '\'' +
                ", adresse='" + adresse + '\'' +
                ", surAutoroute=" + surAutoroute +
                ", dateIncident=" + dateIncident +
                ", dateSouhaiteeIntervention=" + dateSouhaiteeIntervention +
                ", prenomContact='" + prenomContact + '\'' +
                ", nomContact='" + nomContact + '\'' +
                ", telephoneContact='" + telephoneContact + '\'' +
                ", statut=" + statut +
                ", dateCreation=" + dateCreation +
                '}';
    }
}
