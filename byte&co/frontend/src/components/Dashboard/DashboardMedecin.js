import React from 'react';
import { FaUserCircle } from 'react-icons/fa';
import DashboardLayout from './DashboardLayout';
import UserProfile from './UserProfile';

const DashboardMedecin = ({ user, onLogout }) => {
  const menuItems = [
    { key: 'profil', label: 'Mes informations personnelles', icon: <FaUserCircle /> },
    { key: 'casAssignes', label: 'Cas Assignés' }
  ];

  const renderContent = (activeMenu) => {
    switch (activeMenu) {
      case 'profil':
        return <UserProfile user={user} />;
      case 'casAssignes':
        return <h2>Liste des cas assignés</h2>;
      default:
        return <h2>Contenu non disponible</h2>;
    }
  };

  return <DashboardLayout user={user} onLogout={onLogout} menuItems={menuItems} renderContent={renderContent} />;
};

export default DashboardMedecin;