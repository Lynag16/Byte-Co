package com.glop.gestionsinistres.dto.sinistre;

import com.glop.gestionsinistres.model.sinistre.TypeSinistre;

import java.time.LocalDate;

public abstract class SinistreDTO {

    private String description;
    private LocalDate dateDeclaration;
    private TypeSinistre typeSinistre;

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public LocalDate getDateDeclaration() { return dateDeclaration; }
    public void setDateDeclaration(LocalDate dateDeclaration) { this.dateDeclaration = dateDeclaration; }

    public TypeSinistre getTypeSinistre() { return typeSinistre; }
    public void setTypeSinistre(TypeSinistre typeSinistre) { this.typeSinistre = typeSinistre; }

}