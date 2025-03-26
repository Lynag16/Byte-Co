import React, { useState } from 'react';
import { FaHome, FaUsers, FaFileContract, FaTools } from 'react-icons/fa';
import GestionOffres from '../GestionOffres/GestionOffres';
import './Dashboard.css';
import Footer from '../../components/Shared/Footer';

const DashboardAdmin = ({ user, onLogout }) => {
  const [activeMenu, setActiveMenu] = useState('tableauDeBord');

  const menuItems = [
    { key: 'tableauDeBord', label: 'Tableau de Bord', icon: <FaHome /> },
    { key: 'utilisateurs', label: 'Utilisateurs', icon: <FaUsers /> },
    { key: 'contrats', label: 'Contrats', icon: <FaFileContract /> },
    { key: 'gestionOffres', label: 'Gestion des Offres', icon: <FaTools /> },
  ];

  const renderContent = () => {
    switch (activeMenu) {
      case 'tableauDeBord':
        return <h2>Bienvenue, administrateur</h2>;
      case 'utilisateurs':
        return <h2>Gestion des utilisateurs</h2>;
      case 'contrats':
        return <h2>Contrats</h2>;
      case 'gestionOffres':
        return <GestionOffres />;
      default:
        return <h2>Contenu non disponible</h2>;
    }
  };

  return (
    <>
      <div className="dashboard-container">
        <aside className="sidebar">
          <div className="sidebar-header">
            <h1>Admin</h1>
          </div>
          <ul className="menu-list">
            {menuItems.map(menu => (
              <li
                key={menu.key}
                className={activeMenu === menu.key ? 'active' : ''}
                onClick={() => setActiveMenu(menu.key)}
              >
                <span className="menu-icon">{menu.icon}</span>
                <span className="menu-label">{menu.label}</span>
              </li>
            ))}
          </ul>
        </aside>
        <main className="dashboard-content">
          <header className="dashboard-header">
            <h2>{user.email}</h2>
            <button onClick={onLogout}>Déconnexion</button>
          </header>
          <section className="content-area">{renderContent()}</section>
        </main>
      </div>
      <Footer />
    </>
  );
};

export default DashboardAdmin;
