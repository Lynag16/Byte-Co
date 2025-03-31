package com.glop.empreintecarbone;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.List;
public interface TrajetRepository extends JpaRepository<Trajet, Long> {
    // Vous pouvez ajouter des méthodes spécifiques ici si nécessaire
    List<Trajet> findByIdClient(Long idClient);
}


