package com.glop.gestionsinistres.model.assistance;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.time.LocalDate;
import java.util.Objects;

@Entity
public class DemandeDepannage {

    @Id
    @GeneratedValue
    private Long id;

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

    public DemandeDepannage() {
        this.statut = StatutDemande.EN_ATTENTE;
        this.dateCreation = LocalDate.now();
    }

    public DemandeDepannage(String userId, String immatriculation, String categorieProbleme, String typeEnergie,
                            String adresse, boolean surAutoroute, LocalDate dateIncident,
                            LocalDate dateSouhaiteeIntervention, String prenomContact, String nomContact,
                            String telephoneContact) {
        this.userId = userId;
        this.immatriculation = immatriculation;
        this.categorieProbleme = categorieProbleme;
        this.typeEnergie = typeEnergie;
        this.adresse = adresse;
        this.surAutoroute = surAutoroute;
        this.dateIncident = dateIncident;
        this.dateSouhaiteeIntervention = dateSouhaiteeIntervention;
        this.prenomContact = prenomContact;
        this.nomContact = nomContact;
        this.telephoneContact = telephoneContact;
        this.statut = StatutDemande.EN_ATTENTE;
        this.dateCreation = LocalDate.now();
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DemandeDepannage)) return false;
        DemandeDepannage that = (DemandeDepannage) o;
        return surAutoroute == that.surAutoroute && id.equals(that.id) && userId.equals(that.userId) &&
                immatriculation.equals(that.immatriculation) && categorieProbleme.equals(that.categorieProbleme) &&
                typeEnergie.equals(that.typeEnergie) && adresse.equals(that.adresse) &&
                dateIncident.equals(that.dateIncident) && dateSouhaiteeIntervention.equals(that.dateSouhaiteeIntervention) &&
                prenomContact.equals(that.prenomContact) && nomContact.equals(that.nomContact) &&
                telephoneContact.equals(that.telephoneContact) && statut == that.statut &&
                dateCreation.equals(that.dateCreation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, immatriculation, categorieProbleme, typeEnergie, adresse,
                surAutoroute, dateIncident, dateSouhaiteeIntervention, prenomContact, nomContact,
                telephoneContact, statut, dateCreation);
    }

    @Override
    public String toString() {
        return "DemandeDepannage{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
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
