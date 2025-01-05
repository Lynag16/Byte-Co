package glop.gestioncontrats.dto;

import java.util.List;

public class OfferDTO {

    private int id;
    private String nomOffer;
    private float prix;
    private String descriptionOffer;
    private String conditionsEligibilite;
    private List<Integer> contractIds; // Liste des IDs des contrats associés (au lieu de l'objet complet)

    // Constructeurs
    public OfferDTO() {
    }

    public OfferDTO(int id, String nomOffer, float prix, String descriptionOffer, String conditionsEligibilite, List<Integer> contractIds) {
        this.id = id;
        this.nomOffer = nomOffer;
        this.prix = prix;
        this.descriptionOffer = descriptionOffer;
        this.conditionsEligibilite = conditionsEligibilite;
        this.contractIds = contractIds;
    }

    // Getters et Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomOffer() {
        return nomOffer;
    }

    public void setNomOffer(String nomOffer) {
        this.nomOffer = nomOffer;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public String getDescriptionOffer() {
        return descriptionOffer;
    }

    public void setDescriptionOffer(String descriptionOffer) {
        this.descriptionOffer = descriptionOffer;
    }

    public String getConditionsEligibilite() {
        return conditionsEligibilite;
    }

    public void setConditionsEligibilite(String conditionsEligibilite) {
        this.conditionsEligibilite = conditionsEligibilite;
    }

    public List<Integer> getContractIds() {
        return contractIds;
    }

    public void setContractIds(List<Integer> contractIds) {
        this.contractIds = contractIds;
    }
}
