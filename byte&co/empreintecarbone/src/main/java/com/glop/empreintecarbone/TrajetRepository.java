package com.glop.empreintecarbone;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TrajetRepository extends JpaRepository<Trajet, Long> {
    // Vous pouvez ajouter des méthodes spécifiques ici si nécessaire
}
