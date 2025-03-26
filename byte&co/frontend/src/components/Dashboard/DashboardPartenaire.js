import React, { useState } from 'react';
import { FaHome, FaHandsHelping } from 'react-icons/fa';
import './Dashboard.css';
import Footer from '../../components/Shared/Footer';

const DashboardPartenaire = ({ user, onLogout }) => {
  const [activeMenu, setActiveMenu] = useState('tableauDeBord');

  const menuItems = [
    { key: 'tableauDeBord', label: 'Tableau de Bord', icon: <FaHome /> },
    { key: 'interventions', label: 'Mes Interventions', icon: <FaHandsHelping /> }
  ];

  const renderContent = () => {
    switch (activeMenu) {
      case 'tableauDeBord':
        return <h2>Bienvenue Partenaire</h2>;
      case 'interventions':
        return <h2>Vos interventions</h2>;
      default:
        return <h2>Contenu non disponible</h2>;
    }
  };

  return (
    <>
      <div className="dashboard-container">
        <aside className="sidebar">
          <div className="sidebar-header">
            <h1>Partenaire</h1>
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

export default DashboardPartenaire;
