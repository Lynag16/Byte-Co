import React from 'react';
import { FaFileAlt, FaFileContract, FaUserCircle, FaMoneyBill, FaBell, FaCogs, FaLifeRing, FaFilePdf } from 'react-icons/fa';
import DashboardLayout from './DashboardLayout';
import UserProfile from './UserProfile';
import Parametres from './Parametres';

const DashboardClient = ({ user, onLogout }) => {
  const menuItems = [
    { key: 'profil', label: 'Mes informations personnelles', icon: <FaUserCircle /> },
    { key: 'contrats', label: 'Mes Contrats', icon: <FaFileContract /> },
    { key: 'paiements', label: 'Paiements', icon: <FaMoneyBill /> },
    { key: 'assistance', label: 'Assistance', icon: <FaLifeRing /> },
    { key: 'documents', label: 'Documents', icon: <FaFilePdf /> },
    { key: 'notifications', label: 'Notifications', icon: <FaBell /> },
    { key: 'parametres', label: 'Paramètres', icon: <FaCogs /> }
  ];

  const renderContent = (activeMenu) => {
    switch (activeMenu) {
      case 'profil':
        return <UserProfile user={user} />;
      case 'contrats':
        return <h2>Vos contrats</h2>;
      case 'paiements':
        return <h2>Historique des paiements</h2>;
      case 'assistance':
        return <h2>Demander une assistance</h2>;
      case 'documents':
        return <h2>Vos documents</h2>;
      case 'notifications':
        return <h2>Vos notifications</h2>;
      case 'parametres':
        return <Parametres user={user} />;
      default:
        return <h2>Contenu non disponible</h2>;
    }
  };

  return <DashboardLayout user={user} onLogout={onLogout} menuItems={menuItems} renderContent={renderContent} />;
};

export default DashboardClient;