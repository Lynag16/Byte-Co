package glop.gestioncontrats;

import java.time.format.DateTimeFormatter;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Contract {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long id;

    private String contractType;
    private DateTimeFormatter startDate;
    private DateTimeFormatter endDate;
    private String status;
    private String documents;
    private float amount;

    @ManyToOne
    @JoinColumn(name = "client_Id", nullable = false)
    private Client client;

    // @ManyToOne
    // @JoinColumn(name = "Offer_Id", nullable = false)
    // private Offer offer;

    public Contract() {
    }

    public Contract(Client client, String contractType, DateTimeFormatter startDate, DateTimeFormatter endDate, String status, String documents, float amount) {
        this.client = client;
        this.contractType = contractType;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.documents = documents;
        this.amount = amount;
    }

    public long getId() {
        return id;
    }

    public Client getClient() {
        return client;
    }

    public String getContractType() {
        return contractType;
    }

    public DateTimeFormatter getStartDate() {
        return startDate;
    }

    public DateTimeFormatter getEndDate() {
        return endDate;
    }

    public String getStatus() {
        return status;
    }

    public String getDocuments() {
        return documents;
    }

    public float getAmount() {
        return amount;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setContractType(String contractType) {
        this.contractType = contractType;
    }

    public void setStartDate(DateTimeFormatter startDate) {
        this.startDate = startDate;
    }






    

}
