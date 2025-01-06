package glop.gestioncontrats.dto;

import java.time.LocalDateTime;

public class ContractDTO {

    private int id;
    private int offerId;
    private String typeAssurance;
    private LocalDateTime dateSouscription;
    private LocalDateTime dateExpiration;
    private String statut;
    private float montantContrat;
    private float empreinteCalculee;
    private float compensationCarbone;

    // Constructeurs
    public ContractDTO() {
    }

    public ContractDTO(int id, int offerId, String typeAssurance, LocalDateTime dateSouscription, LocalDateTime dateExpiration,
                       String statut, float montantContrat, float empreinteCalculee, float compensationCarbone) {
        this.id = id;
        this.offerId = offerId;
        this.typeAssurance = typeAssurance;
        this.dateSouscription = dateSouscription;
        this.dateExpiration = dateExpiration;
        this.statut = statut;
        this.montantContrat = montantContrat;
        this.empreinteCalculee = empreinteCalculee;
        this.compensationCarbone = compensationCarbone;
    }

    // Getters et Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOfferId() {
        return offerId;
    }

    public void setOfferId(int offerId) {
        this.offerId = offerId;
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

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
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
}