package com.glop.gestionsinistres.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "typeSinistre"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = AccidentRouteSinistre.class, name = "ACCIDENT_ROUTE"),
        @JsonSubTypes.Type(value = IncidentMedicalSinistre.class, name = "INCIDENT_MEDICAL"),
        @JsonSubTypes.Type(value = ProblemeHebergementSinistre.class, name = "PROBLEME_HEBERGEMENT"),
        @JsonSubTypes.Type(value = RetardTransportSinistre.class, name = "RETARD_TRANSPORT"),
})
public abstract class Sinistre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    @Enumerated(EnumType.STRING)
    private TypeSinistre type;
    private LocalDate dateDeclaration;
    @Enumerated(EnumType.STRING)
    private StatutSinistre statut;
    private String userId;


    public Sinistre() {
        this.statut = StatutSinistre.EN_ATTENTE;
        this.dateDeclaration = LocalDate.now();
    }

    public Sinistre(String description, TypeSinistre type, LocalDate dateDeclaration, String userId) {
        this.description = description;
        this.type = type;
        this.dateDeclaration = (dateDeclaration == null) ? LocalDate.now() : dateDeclaration;
        this.userId = userId;
        this.statut = StatutSinistre.EN_ATTENTE;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public TypeSinistre getType() { return type; }
    public void setType(TypeSinistre type) { this.type = type; }

    public LocalDate getDateDeclaration() { return dateDeclaration; }
    public void setDateDeclaration(LocalDate dateDeclaration) { this.dateDeclaration = dateDeclaration; }

    public StatutSinistre getStatut() { return statut; }
    public void setStatut(StatutSinistre statut) { this.statut = statut; }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Sinistre)) return false;
        Sinistre sinistre = (Sinistre) o;
        return Objects.equals(id, sinistre.id) &&
                Objects.equals(description, sinistre.description) &&
                type == sinistre.type &&
                Objects.equals(dateDeclaration, sinistre.dateDeclaration) &&
                statut == sinistre.statut &&
                Objects.equals(userId, sinistre.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getDescription(), getType(), getDateDeclaration(), getStatut(), getUserId());
    }

    @Override
    public String toString() {
        return "Sinistre{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", type=" + type +
                ", dateDeclaration=" + dateDeclaration +
                ", statut=" + statut +
                ", userId='" + userId + '\'' +
                '}';
    }
}
