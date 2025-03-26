import React, { useState } from 'react';
import { FaHome, FaFileAlt, FaFileContract } from 'react-icons/fa';
import './Dashboard.css';
import Footer from '../../components/Shared/Footer';

const DashboardClient = ({ user, onLogout }) => {
  const [activeMenu, setActiveMenu] = useState('tableauDeBord');

  const menuItems = [
    { key: 'tableauDeBord', label: 'Tableau de Bord', icon: <FaHome /> },
    { key: 'reclamations', label: 'Réclamations', icon: <FaFileAlt /> },
    { key: 'contrats', label: 'Mes Contrats', icon: <FaFileContract /> }
  ];

  const renderContent = () => {
    switch (activeMenu) {
      case 'tableauDeBord':
        return <h2>Bienvenue dans votre espace client</h2>;
      case 'reclamations':
        return <h2>Vos réclamations</h2>;
      case 'contrats':
        return <h2>Vos contrats</h2>;
      default:
        return <h2>Contenu non disponible</h2>;
    }
  };

  return (
    <>
      <div className="dashboard-container">
        <aside className="sidebar">
          <div className="sidebar-header">
            <h1>Client</h1>
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

export default DashboardClient;
