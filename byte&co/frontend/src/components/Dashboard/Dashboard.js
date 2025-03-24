import React, { useState } from "react";
import GestionOffres from "../GestionOffres/GestionOffres";
import "./Dashboard.css";
import { useNavigate } from "react-router-dom";
import { useSelector, useDispatch } from "react-redux";
import { logoutUser } from "../../store/authSlice";

const Dashboard = () => {
  const navigate = useNavigate();
  const dispatch = useDispatch();
  const user = useSelector((state) => state.auth.user);

  const [activeMenu, setActiveMenu] = useState("tableauDeBord");

  const menuItems = {
    client: [
      { key: "tableauDeBord", label: "Tableau de Bord" },
      { key: "reclamations", label: "Réclamations" },
      { key: "contrats", label: "Mes Contrats" },
    ],
    gestionnaire: [
      { key: "tableauDeBord", label: "Tableau de Bord" },
      { key: "utilisateurs", label: "Utilisateurs" },
      { key: "contrats", label: "Contrats" },
      { key: "gestionOffres", label: "Gestion des Offres" },
    ],
    medecin: [
      { key: "tableauDeBord", label: "Tableau de Bord" },
      { key: "casAssignes", label: "Cas Assignés" },
    ],
    partenaire: [
      { key: "tableauDeBord", label: "Tableau de Bord" },
      { key: "interventions", label: "Mes Interventions" },
    ],
  };

  const userRole = user?.role || "client";
  const menus = menuItems[userRole] || [];

  const renderContent = () => {
    switch (activeMenu) {
      case "tableauDeBord":
        return <h2>Bienvenue sur votre tableau de bord</h2>;
      case "utilisateurs":
        return <h2>Gestion des utilisateurs</h2>;
      case "contrats":
        return <h2>Gestion des contrats</h2>;
      case "reclamations":
        return <h2>Gestion des réclamations</h2>;
      case "casAssignes":
        return <h2>Cas assignés aux médecins</h2>;
      case "interventions":
        return <h2>Interventions des partenaires</h2>;
      case "gestionOffres":
        return <GestionOffres />;
      default:
        return <h2>Contenu non disponible</h2>;
    }
  };

  const handleLogout = () => {
    dispatch(logoutUser());
    navigate("/login");
  };

  return (
    <div className="dashboard-container">
      {/* Barre latérale */}
      <aside className="sidebar">
        <div className="sidebar-header">
          <h1>AssurMob</h1>
        </div>
        <ul className="menu-list">
          {menus.map((menu) => (
            <li
              key={menu.key}
              className={activeMenu === menu.key ? "active" : ""}
              onClick={() => setActiveMenu(menu.key)}
            >
              {menu.label}
            </li>
          ))}
        </ul>
      </aside>

      {/* Contenu principal */}
      <main className="dashboard-content">
        <header className="dashboard-header">
          <h2>
            Bienvenue : {user?.Prenom} {user?.Nom}!
          </h2>
          <button onClick={handleLogout}>Déconnexion</button>
        </header>
        <section className="content-area">{renderContent()}</section>
      </main>
    </div>
  );
};

export default Dashboard;
