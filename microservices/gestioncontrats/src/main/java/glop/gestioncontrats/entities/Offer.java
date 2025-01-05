package glop.gestioncontrats.entities;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Offer {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;

    private String nomOffer;
    private float prix;
    private String descriptionOffer;
    private String conditionsEligibilite;

    public Offer() {
    }

    //relation avec la table contrat 
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "offer")
    private List<Contract> contracts;

    public Offer(int id, String nomOffer, float prix, String descriptionOffer, String conditionsEligibilite) {
        this.id = id;
        this.nomOffer = nomOffer;
        this.prix = prix;
        this.descriptionOffer = descriptionOffer;
        this.conditionsEligibilite = conditionsEligibilite;
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

    public List<Contract> getContracts() {
        return contracts;
    }

    public void setContracts(List<Contract> contracts) {
        this.contracts = contracts;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
