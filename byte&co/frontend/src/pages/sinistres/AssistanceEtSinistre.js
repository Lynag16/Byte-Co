import React from "react";
import { AlertTriangle, PhoneCall, Truck, FileText, Wrench } from "lucide-react";
import { useNavigate } from "react-router-dom";
import { motion } from "framer-motion";
import "./AssistanceEtSinistre.css";

const actions = [
  {
    icon: <AlertTriangle className="assistance-card-icon w-6 h-6" />,
    title: "Déclarer un sinistre",
    description: "Accident, vol, blessure ou autre incident",
    route: "/declaration-sinistre",
  },
  {
    icon: <PhoneCall className="assistance-card-icon w-6 h-6" />,
    title: "Obtenir de l'assistance 24/7",
    description: "Urgences médicales, logistiques ou techniques",
    route: "/assistance",
  },
  {
    icon: <Truck className="assistance-card-icon w-6 h-6" />,
    title: "Demander une dépanneuse",
    description: "Pour tout type de véhicule, même alternatif",
    route: "/depannage",
  },
  {
    icon: <FileText className="assistance-card-icon w-6 h-6" />,
    title: "Suivre un sinistre",
    description: "Documents, prise en charge, évolution du dossier",
    route: "/suivi-sinistre",
  },
  {
    icon: <Wrench className="assistance-card-icon w-6 h-6" />,
    title: "Trouver un réparateur partenaire",
    description: "Garages certifiés en France et à l'international",
    route: "/reparateurs",
  },
];

export default function AssistanceEtSinistre() {
  const navigate = useNavigate();

  return (
    <div className="assistance-container">
      <motion.h1
        initial={{ opacity: 0, y: -20 }}
        animate={{ opacity: 1, y: 0 }}
        transition={{ duration: 0.5 }}
        className="assistance-title"
      >
        <span>Assistance</span> et <span>sinistre</span>
      </motion.h1>

      <p className="assistance-subtitle">
        Comment pouvons-nous vous aider ?
      </p>

      <div className="assistance-grid">
        {actions.map((action, index) => (
          <div
            key={index}
            className="assistance-card"
            onClick={() => navigate(action.route)}
          >
            {action.icon}
            <div className="assistance-card-content">
              <p className="assistance-card-title">{action.title}</p>
              <p className="assistance-card-description">{action.description}</p>
            </div>
          </div>
        ))}
      </div>

      <div className="assistance-button-wrapper">
        <button className="assistance-button" onClick={() => navigate("/")}>
          Retour à l'accueil
        </button>
      </div>
    </div>
  );
}
