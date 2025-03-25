package com.glop.gestionutilisateurs.entities;

import java.util.Date;

import com.glop.gestionutilisateurs.entities.Utilisateurs;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
@DiscriminatorValue("CLIENT")
public class Client extends Utilisateurs {
    private String languePreference;
    private String monnaiePreference;
    private String badge;
    private String statut;

}
