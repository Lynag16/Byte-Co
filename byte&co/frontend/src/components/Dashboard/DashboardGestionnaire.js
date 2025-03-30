import React from 'react';
import { FaFileAlt, FaFileContract, FaHandshake, FaTools, FaUsers, FaUserCircle, FaMoneyBill, FaBell, FaCogs, FaLifeRing, FaFilePdf } from 'react-icons/fa';
import GestionOffres from '../GestionOffres/GestionOffres';
import DashboardLayout from './DashboardLayout';
import PersonnelProfile from './PersonnelProfile';
import GestionPersonnel from './GestionPersonnel';
import Parametres from './Parametres'
import TousLesSinistresPersonnel from './sinistres/TousLesSinistresPersonnel';

const DashboardGestionnaire = ({ user, onLogout }) => {
  const menuItems = [
    { key: 'profil', label: 'Mes informations personnelles', icon: <FaUserCircle /> },
    { key: 'personnel', label: 'Gestions du Personnel', icon: <FaUsers /> },
    { key: 'partenaires', label: 'Gestion des Partenaires', icon: <FaHandshake /> },
    { key: 'sinistres', label: 'Tous les Sinistres', icon: <FaFileContract /> },
    { key: 'gestionOffres', label: 'Gestion des Offres', icon: <FaTools /> },
    { key: 'contrats', label: 'Contrats', icon: <FaFileContract /> },
    { key: 'parametres', label: 'Paramètres', icon: <FaCogs /> }
  ];

  const renderContent = (activeMenu) => {
    switch (activeMenu) {
      case 'profil':
        return <PersonnelProfile user={user} />;
      case 'sinistres':
        return <TousLesSinistresPersonnel />;
      case 'personnel':
        return <GestionPersonnel />;
      case 'contrats':
        return <h2>Contrats</h2>;
      case 'gestionOffres':
        return <GestionOffres />;
      case 'parametres':
        return <Parametres user={user} />;
      default:
        return <h2>Contenu non disponible</h2>;
    }
  };

  return <DashboardLayout user={user} onLogout={onLogout} menuItems={menuItems} renderContent={renderContent} />;
};

export default DashboardGestionnaire;