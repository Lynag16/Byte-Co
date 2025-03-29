import React from 'react';
import { FaFileAlt, FaFileContract, FaTools, FaUsers, FaUserCircle, FaMoneyBill, FaBell, FaCogs, FaLifeRing, FaFilePdf } from 'react-icons/fa';
import GestionOffres from '../GestionOffres/GestionOffres';
import DashboardLayout from './DashboardLayout';
import UserProfile from './UserProfile';
import Parametres from './Parametres'

const DashboardAdmin = ({ user, onLogout }) => {
  const menuItems = [
    { key: 'profil', label: 'Mes informations personnelles', icon: <FaUserCircle /> },
    { key: 'utilisateurs', label: 'Utilisateurs', icon: <FaUsers /> },
    { key: 'contrats', label: 'Contrats', icon: <FaFileContract /> },
    { key: 'gestionOffres', label: 'Gestion des Offres', icon: <FaTools /> },
    { key: 'parametres', label: 'Paramètres', icon: <FaCogs /> }
  ];

  const renderContent = (activeMenu) => {
    switch (activeMenu) {
      case 'profil':
        return <UserProfile user={user} />;
      case 'utilisateurs':
        return <h2>Gestion des utilisateurs</h2>;
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

export default DashboardAdmin;