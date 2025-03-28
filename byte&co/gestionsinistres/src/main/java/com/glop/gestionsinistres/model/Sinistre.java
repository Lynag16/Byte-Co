package com.glop.gestionsinistres.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Sinistre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    @Enumerated(EnumType.STRING)
    private TypeSinistre type;
    private LocalDateTime dateDeclaration;
    @Enumerated(EnumType.STRING)
    private StatutSinistre statut;
    private String userId;

    public Sinistre() {
        this.statut = StatutSinistre.EN_ATTENTE;
    }

    public Sinistre(String description, TypeSinistre type, LocalDateTime dateDeclaration, String userId) {
        this.description = description;
        this.type = type;
        this.dateDeclaration = dateDeclaration;
        this.userId = userId;
        this.statut = StatutSinistre.EN_ATTENTE;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public TypeSinistre getType() { return type; }
    public void setType(TypeSinistre type) { this.type = type; }

    public LocalDateTime getDateDeclaration() { return dateDeclaration; }
    public void setDateDeclaration(LocalDateTime dateDeclaration) { this.dateDeclaration = dateDeclaration; }

    public StatutSinistre getStatut() { return statut; }
    public void setStatut(StatutSinistre statut) { this.statut = statut; }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Sinistre sinistre)) return false;
        return Objects.equals(getId(), sinistre.getId()) && Objects.equals(getDescription(), sinistre.getDescription()) && getType() == sinistre.getType() && Objects.equals(getDateDeclaration(), sinistre.getDateDeclaration()) && getStatut() == sinistre.getStatut() && Objects.equals(getUserId(), sinistre.getUserId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getDescription(), getType(), getDateDeclaration(), getStatut(), getUserId());
    }
}
