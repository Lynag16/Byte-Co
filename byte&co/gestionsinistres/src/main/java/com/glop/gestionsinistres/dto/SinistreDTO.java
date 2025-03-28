package com.glop.gestionsinistres.dto;

import java.time.LocalDateTime;

public abstract class SinistreDTO {

    private String description;
    private LocalDateTime dateDeclaration;

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public LocalDateTime getDateDeclaration() { return dateDeclaration; }
    public void setDateDeclaration(LocalDateTime dateDeclaration) { this.dateDeclaration = dateDeclaration; }

}