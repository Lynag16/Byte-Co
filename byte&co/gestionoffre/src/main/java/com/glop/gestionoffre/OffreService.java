package com.glop.gestionoffre;



import com.glop.gestionoffre.OffreDTO;






import java.util.List;

public interface OffreService {
    List<OffreDTO> getAllOffres();
    OffreDTO getOffreById(Long id);
    OffreDTO createOffre(OffreDTO offreDTO);
    OffreDTO updateOffre(Long id, OffreDTO offreDTO);
    void deleteOffre(Long id);
}
