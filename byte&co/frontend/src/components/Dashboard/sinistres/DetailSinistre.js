import React, { useEffect, useState } from "react";
import axios from "axios";
import { useParams, useNavigate } from "react-router-dom";

const DetailSinistre = ({ token }) => {
  const { id } = useParams();
  const [sinistre, setSinistre] = useState(null);
  const navigate = useNavigate();

  useEffect(() => {
    axios.get(`http://localhost:8080/api/admin/sinistres/${id}`, {
      headers: { Authorization: `Bearer ${token}` }
    }).then(res => setSinistre(res.data))
      .catch(() => navigate("/admin/sinistres"));
  }, [id, token, navigate]);

  if (!sinistre) return <p>Chargement...</p>;

  return (
    <div>
      <h2>Détail du sinistre</h2>
      <p><strong>ID :</strong> {sinistre.id}</p>
      <p><strong>Type :</strong> {sinistre.type}</p>
      <p><strong>Statut :</strong> {sinistre.statut}</p>
      <p><strong>Date :</strong> {new Date(sinistre.dateDeclaration).toLocaleString()}</p>
      <p><strong>Utilisateur :</strong> {sinistre.userId}</p>

      {/* Champs dynamiques selon le type */}
      {sinistre.type === "AUTO" && (
        <>
          <p><strong>Immatriculation :</strong> {sinistre.immatriculation}</p>
          <p><strong>Lieu :</strong> {sinistre.lieuAccident}</p>
          <p><strong>Description :</strong> {sinistre.description}</p>
        </>
      )}
      {sinistre.type === "HABITATION" && (
        <>
          <p><strong>Adresse bien :</strong> {sinistre.adresseBien}</p>
          <p><strong>Type dégât :</strong> {sinistre.typeDegat}</p>
          <p><strong>Estimation :</strong> {sinistre.estimationCout} €</p>
        </>
      )}
      {sinistre.type === "SANTE" && (
        <>
          <p><strong>Nom patient :</strong> {sinistre.nomPatient}</p>
          <p><strong>Intervention :</strong> {sinistre.typeIntervention}</p>
          <p><strong>Coût :</strong> {sinistre.coutIntervention} €</p>
        </>
      )}

      <button onClick={() => navigate("/admin/sinistres")}>Retour à la liste</button>
    </div>
  );
};

export default DetailSinistre;
