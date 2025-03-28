import React from 'react';
import { FaUserCircle, FaCogs, FaHandsHelping } from 'react-icons/fa';
import DashboardLayout from './DashboardLayout';
import UserProfile from './UserProfile';
import Parametres from './Parametres';

const DashboardPartenaire = ({ user, onLogout }) => {
  const menuItems = [
    { key: 'profil', label: 'Mes informations personnelles', icon: <FaUserCircle /> },
    { key: 'interventions', label: 'Mes Interventions', icon: <FaHandsHelping /> },
    { key: 'parametres', label: 'Paramètres', icon: <FaCogs /> }
  ];

  const renderContent = (activeMenu) => {
    switch (activeMenu) {
      case 'profil':
        return <UserProfile user={user} />;
      case 'interventions':
        return <h2>Vos interventions</h2>;
      case 'parametres':
        return <Parametres user={user} />;
      default:
        return <h2>Contenu non disponible</h2>;
    }
  };

  return <DashboardLayout user={user} onLogout={onLogout} menuItems={menuItems} renderContent={renderContent} />;
};

export default DashboardPartenaire;