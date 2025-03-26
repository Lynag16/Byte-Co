import React, { useState, useEffect } from "react";
import axios from "axios";
import "./DeclarationSinistre.css";

const DeclarationSinistre = () => {
  const [sinistres, setSinistres] = useState([]);
  const [form, setForm] = useState({
    description: "",
    typeSinistre: "Accident de la route",
    dateDeclaration: "",
    fichier: null,
    // champs dynamiques
    objetPerdu: "",
    lieuIncident: "",
    valeurEstimee: "",
    nombreBlesses: "",
    nomHopital: "",
    panneType: "",
    lieuHébergement: "",
    retardHeures: "",
    transportConcerné: "",
    objetEndommagé: "",
    degatsEstimes: ""
  });

  useEffect(() => {
    axios
      .get("http://localhost:8085/api/sinistres")
      .then((res) => setSinistres(res.data))
      .catch((err) => console.error("Erreur chargement sinistres :", err));
  }, []);

  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleFileChange = (e) => {
    setForm({ ...form, fichier: e.target.files[0] });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    const formData = new FormData();

    // Champs de base
    formData.append("description", form.description);
    formData.append("typeSinistre", form.typeSinistre);
    formData.append("dateDeclaration", form.dateDeclaration);
    if (form.fichier) formData.append("fichier", form.fichier);

    // Champs dynamiques
    formData.append("objetPerdu", form.objetPerdu);
    formData.append("lieuIncident", form.lieuIncident);
    formData.append("valeurEstimee", form.valeurEstimee);
    formData.append("nombreBlesses", form.nombreBlesses);
    formData.append("nomHopital", form.nomHopital);
    formData.append("panneType", form.panneType);
    formData.append("lieuHébergement", form.lieuHébergement);
    formData.append("retardHeures", form.retardHeures);
    formData.append("transportConcerné", form.transportConcerné);
    formData.append("objetEndommagé", form.objetEndommagé);
    formData.append("degatsEstimes", form.degatsEstimes);

    try {
      await axios.post("http://localhost:8085/api/sinistres", formData, {
        headers: { "Content-Type": "multipart/form-data" },
      });
      alert("Sinistre déclaré avec succès !");
      window.location.reload();
    } catch (err) {
      console.error("Erreur déclaration :", err);
      alert("Échec de la déclaration !");
    }
  };

  const renderChampsSpecifiques = () => {
    switch (form.typeSinistre) {
      case "Accident de la route":
        return (
          <>
            <label>Nombre de véhicules impliqués :</label>
            <input type="number" name="nombreVehicules" value={form.nombreVehicules} onChange={handleChange} />
            <label>Lieu de l'accident :</label>
            <input type="text" name="lieuIncident" value={form.lieuIncident} onChange={handleChange} />
            <label>Nombre de blessés :</label>
            <input type="number" name="nombreBlesses" value={form.nombreBlesses} onChange={handleChange} />
          </>
        );
      case "Vol ou perte d’objet personnel":
        return (
          <>
            <label>Objet perdu ou volé :</label>
            <input type="text" name="objetPerdu" value={form.objetPerdu} onChange={handleChange} />
            <label>Lieu de l'incident :</label>
            <input type="text" name="lieuIncident" value={form.lieuIncident} onChange={handleChange} />
            <label>Valeur estimée (€) :</label>
            <input type="number" name="valeurEstimee" value={form.valeurEstimee} onChange={handleChange} />
          </>
        );
      case "Incident médical":
        return (
          <>
            <label>Nombre de personnes blessées :</label>
            <input type="number" name="nombreBlesses" value={form.nombreBlesses} onChange={handleChange} />
            <label>Nom de l’hôpital ou du médecin :</label>
            <input type="text" name="nomHopital" value={form.nomHopital} onChange={handleChange} />
            <label>Lieu de l'incident :</label>
            <input type="text" name="lieuIncident" value={form.lieuIncident} onChange={handleChange} />
          </>
        );
      case "Panne ou immobilisation":
        return (
          <>
            <label>Type de panne :</label>
            <input type="text" name="panneType" value={form.panneType} onChange={handleChange} />
            <label>Lieu de l’immobilisation :</label>
            <input type="text" name="lieuIncident" value={form.lieuIncident} onChange={handleChange} />
          </>
        );
      case "Problème d’hébergement":
        return (
          <>
            <label>Nom de l’hébergement :</label>
            <input type="text" name="lieuHébergement" value={form.lieuHébergement} onChange={handleChange} />
            <label>Adresse / ville :</label>
            <input type="text" name="lieuIncident" value={form.lieuIncident} onChange={handleChange} />
          </>
        );
      case "Retard ou annulation de transport":
        return (
          <>
            <label>Heures de retard :</label>
            <input type="number" name="retardHeures" value={form.retardHeures} onChange={handleChange} />
            <label>Moyen de transport concerné :</label>
            <input type="text" name="transportConcerné" value={form.transportConcerné} onChange={handleChange} />
          </>
        );
      case "Dommages matériels":
        return (
          <>
            <label>Objet endommagé :</label>
            <input type="text" name="objetEndommagé" value={form.objetEndommagé} onChange={handleChange} />
            <label>Estimation des dégâts (€) :</label>
            <input type="number" name="degatsEstimes" value={form.degatsEstimes} onChange={handleChange} />
          </>
        );
      default:
        return null;
    }
  };

  return (
    <div className="gestion-sinistres-container">
      <h1>Déclarer un Sinistre</h1>

      <form onSubmit={handleSubmit} className="form-container">
        <label>Description :</label>
        <textarea name="description" value={form.description} onChange={handleChange} required />

        <label>Type de Sinistre :</label>
        <select name="typeSinistre" value={form.typeSinistre} onChange={handleChange}>
          <option value="Accident de la route">Accident de la route</option>
          <option value="Panne ou immobilisation">Panne ou immobilisation</option>
          <option value="Vol ou perte d’objet personnel">Vol ou perte d’objet personnel</option>
          <option value="Incident médical">Incident médical</option>
          <option value="Problème d’hébergement">Problème d’hébergement</option>
          <option value="Retard ou annulation de transport">Retard ou annulation de transport</option>
          <option value="Dommages matériels">Dommages matériels</option>
        </select>

        {/* Champs additionnels spécifiques au type */}
        {renderChampsSpecifiques()}

        <label>Date de Déclaration :</label>
        <input type="date" name="dateDeclaration" value={form.dateDeclaration} onChange={handleChange} required />

        <label>Joindre un fichier :</label>
        <input type="file" onChange={handleFileChange} />

        <button type="submit" className="submit-btn">Envoyer la déclaration</button>
      </form>
    </div>
  );
};

export default DeclarationSinistre;
