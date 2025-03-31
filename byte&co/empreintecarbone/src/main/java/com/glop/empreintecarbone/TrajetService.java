package com.glop.empreintecarbone;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import java.util.List;
import com.glop.empreintecarbone.EmpreinteCarboneService;
import com.glop.empreintecarbone.TrajetRepository;
import com.glop.empreintecarbone.TrajetDTO;
import com.glop.empreintecarbone.Trajet;

@Service
public class TrajetService {

    private final TrajetRepository trajetRepository;
    private final EmpreinteCarboneService empreinteCarboneService;

    public TrajetService(TrajetRepository trajetRepository, EmpreinteCarboneService empreinteCarboneService) {
        this.trajetRepository = trajetRepository;
        this.empreinteCarboneService = empreinteCarboneService;
    }

    // Méthode pour extraire l'empreinte carbone de la réponse JSON de l'API
    private Float extractEmpreinteCarbone(String jsonResponse) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(jsonResponse);
            
            // Vérifier si "data" existe
            JsonNode data = root.path("data");
            if (data.isArray() && data.size() > 0) {
                JsonNode firstItem = data.get(0);
                // Vérifier que la clé "value" existe
                JsonNode valueNode = firstItem.path("value");
                if (!valueNode.isMissingNode()) {
                    return valueNode.floatValue();
                }
            }
            return 0.0f;  // Retourner une valeur par défaut si aucune donnée trouvée
        } catch (Exception e) {
            System.out.println("Erreur lors de l'extraction de l'empreinte carbone : " + e.getMessage());
            return 0.0f;
        }
    }
    private String getTransportId(String transportName) {
        if (transportName == null || transportName.isEmpty()) {
            throw new IllegalArgumentException("Transport name cannot be null or empty");
        }
        
        // Exemple de mappage pour obtenir l'ID à partir du nom du transport
        switch (transportName.toLowerCase()) {
            case "avion":
                return "1";
            case "tgv":
                return "2";
            case "intercités":
                return "3";
            case "voiture thermique":
                return "4";
            case "voiture électrique":
                return "5";
            case "autocar thermique":
                return "6";
            case "vélo":
                return "7";
            case "vélo à assistance électrique":
                return "8";
            case "bus thermique":
                return "9";
            case "tramway":
                return "10";
            case "métro":
                return "11";
            case "scooter ou moto légère thermique":
                return "12";
            case "moto thermique":
                return "13";
            case "rer ou transilien":
                return "14";
            case "ter":
                return "15";
            case "bus électrique":
                return "16";
            case "trottinette à assistance électrique":
                return "17";
            case "bus (gnv)":
                return "21";
            case "covoiturage thermique (1 passager)":
                return "22";
            case "covoiturage thermique (2 passagers)":
                return "23";
            case "covoiturage thermique (3 passagers)":
                return "24";
            case "covoiturage thermique (4 passagers)":
                return "25";
            case "covoiturage électrique (1 passager)":
                return "26";
            case "covoiturage électrique (2 passagers)":
                return "27";
            case "covoiturage électrique (3 passagers)":
                return "28";
            case "covoiturage électrique (4 passagers)":
                return "29";
            case "marche":
                return "30";
            default:
                // Retourner une valeur par défaut si le transport n'est pas dans la liste
                return "11"; // Par exemple, retourner l'ID de l'Avion par défaut
        }
    }
    
    
    // Méthode pour créer un trajet
    public Trajet createTrajet(TrajetDTO dto) {
        // Convertir le nom du transport en ID (ex: "voiture thermique" devient "1")
        String transportId = getTransportId(dto.getMoyenTransport());
    
        // Récupérer la distance et la passer en tant que 'km' dans l'API d'empreinte carbone
        int km = dto.getDistance().intValue();  // distance en kilomètres
    
        // Appeler l'API d'empreinte carbone avec la distance et l'ID du transport
        String response = empreinteCarboneService.getCarbonFootprint(km, transportId);
    
        // Extraire l'empreinte carbone de la réponse JSON
        Float empreinte = extractEmpreinteCarbone(response);
    
        // Créer un objet Trajet avec les données du DTO
        Trajet trajet = new Trajet();
        trajet.setIdClient(dto.getIdClient());
        trajet.setDateTrajet(dto.getDateTrajet());
        trajet.setPointDepart(dto.getPointDepart());
        trajet.setPointArrivee(dto.getPointArrivee());
        trajet.setMoyenTransport(dto.getMoyenTransport());
        trajet.setDistance(dto.getDistance());  // La distance du trajet
        trajet.setEmpreinteCarbone(empreinte);  // L'empreinte carbone récupérée
    
        // Sauvegarder le trajet dans la base de données
        return trajetRepository.save(trajet);
    }
    

    // Méthode pour obtenir tous les trajets
    public List<Trajet> getAllTrajets() {
        return trajetRepository.findAll();
    }

    // Méthode pour obtenir un trajet par ID
    public Trajet getTrajetById(Long id) {
        return trajetRepository.findById(id).orElse(null);
    }

    // Méthode pour supprimer un trajet par ID
    public void deleteTrajet(Long id) {
        trajetRepository.deleteById(id);
    }
    public Trajet updateTrajet(Long id, TrajetDTO trajetDTO) {
        Trajet trajet = trajetRepository.findById(id).orElseThrow(() -> new RuntimeException("Trajet not found"));
        
        // Update the fields of trajet
        trajet.setPointDepart(trajetDTO.getPointDepart());
        trajet.setPointArrivee(trajetDTO.getPointArrivee());
        trajet.setMoyenTransport(trajetDTO.getMoyenTransport());
        trajet.setDistance(trajetDTO.getDistance());
        trajet.setDateTrajet(trajetDTO.getDateTrajet());
    
        // Save the updated trajet
        return trajetRepository.save(trajet);
    }// Méthode pour obtenir un trajet par ID Client
    // Méthode pour obtenir un trajet par ID Client
// Méthode pour obtenir un trajet par ID Client
    public List<Trajet> getTrajetByIdClient(Long idClient) {
        List<Trajet> trajets = trajetRepository.findByIdClient(idClient);

        if (trajets.isEmpty()) {
            throw new RuntimeException("No trajet found for client ID " + idClient);
        }
        return trajets; // Retourne la liste de trajets si plusieurs résultats sont trouvés
    }



    
}

