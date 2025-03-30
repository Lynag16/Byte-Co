package com.glop.gestionoffre;

import com.glop.gestionoffre.Offre;

import java.util.List;

public interface OffreService {
    List<Offre> getAllOffres();
    Offre getOffreById(Long id);
    Offre createOffre(Offre offre);
    Offre updateOffre(Long id, Offre offre);
    void deleteOffre(Long id);
}
