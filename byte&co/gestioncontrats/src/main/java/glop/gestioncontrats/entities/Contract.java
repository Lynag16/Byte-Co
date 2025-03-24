package glop.gestioncontrats.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class Contract {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;

    //relation avec la table Offre
    /*@ManyToOne
    @JoinColumn(name = "offerId")
    private Offer offer;
    */
    private int clientId;
    private String typeAssurance;
    private LocalDateTime dateSouscription;
    private LocalDateTime dateExpiration;
    private String statut;
    private float montantContrat;
    private float empreinteCalculee;
    private float compensationCarbone;

    public Contract() {
    }

    public Contract(int id,int clientId, String typeAssurance, LocalDateTime dateSouscription, LocalDateTime dateExpiration, String statut, float montantContrat, float empreinteCalculee, float compensationCarbone) {
        this.id = id;
        this.clientId = clientId;
        this.typeAssurance = typeAssurance;
        this.dateSouscription = dateSouscription;
        this.dateExpiration = dateExpiration;
        this.statut = statut;
        this.montantContrat = montantContrat;
        this.empreinteCalculee = empreinteCalculee;
        this.compensationCarbone = compensationCarbone;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public String getTypeAssurance() {
        return typeAssurance;
    }

    public void setTypeAssurance(String typeAssurance) {
        this.typeAssurance = typeAssurance;
    }

    public LocalDateTime getDateSouscription() {
        return dateSouscription;
    }

    public void setDateSouscription(LocalDateTime dateSouscription) {
        this.dateSouscription = dateSouscription;
    }

    public LocalDateTime getDateExpiration() {
        return dateExpiration;
    }

    public void setDateExpiration(LocalDateTime dateExpiration) {
        this.dateExpiration = dateExpiration;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getMontantContrat() {
        return montantContrat;
    }

    public void setMontantContrat(float montantContrat) {
        this.montantContrat = montantContrat;
    }

    public float getEmpreinteCalculee() {
        return empreinteCalculee;
    }

    public void setEmpreinteCalculee(float empreinteCalculee) {
        this.empreinteCalculee = empreinteCalculee;
    }

    public float getCompensationCarbone() {
        return compensationCarbone;
    }

    public void setCompensationCarbone(float compensationCarbone) {
        this.compensationCarbone = compensationCarbone;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }
}
