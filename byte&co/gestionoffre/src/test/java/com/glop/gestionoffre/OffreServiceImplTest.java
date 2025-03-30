package com.glop.gestionoffre;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Optional;

public class OffreServiceImplTest {

    @Mock
    private OffreRepository offreRepository;

    @InjectMocks
    private OffreServiceImpl offreService;

    private Offre offre;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        offre = new Offre(1L, "Test Offer", 100.0, "Test description", "Test conditions");
    }

    @Test
    void testGetAllOffres() {
        when(offreRepository.findAll()).thenReturn(List.of(offre));
        
        List<Offre> offres = offreService.getAllOffres();
        
        assertNotNull(offres);
        assertEquals(1, offres.size());
        assertEquals("Test Offer", offres.get(0).getNomoffre());
    }

    @Test
    void testGetOffreById() {
        when(offreRepository.findById(1L)).thenReturn(Optional.of(offre));
        
        Offre foundOffre = offreService.getOffreById(1L);
        
        assertNotNull(foundOffre);
        assertEquals("Test Offer", foundOffre.getNomoffre());
    }

    @Test
    void testCreateOffre() {
        when(offreRepository.save(any(Offre.class))).thenReturn(offre);
        
        Offre createdOffre = offreService.createOffre(offre);
        
        assertNotNull(createdOffre);
        assertEquals("Test Offer", createdOffre.getNomoffre());
    }

    @Test
    void testUpdateOffre() {
        when(offreRepository.findById(1L)).thenReturn(Optional.of(offre));
        when(offreRepository.save(any(Offre.class))).thenReturn(offre);
        
        offre.setNomoffre("Updated Offer");
        Offre updatedOffre = offreService.updateOffre(1L, offre);
        
        assertNotNull(updatedOffre);
        assertEquals("Updated Offer", updatedOffre.getNomoffre());
    }

    @Test
    void testDeleteOffre() {
        doNothing().when(offreRepository).deleteById(1L);
        
        offreService.deleteOffre(1L);
        
        verify(offreRepository, times(1)).deleteById(1L);
    }
}