import React, { useState } from 'react';
import './Dashboard.css';
import Footer from '../../components/Shared/Footer';

const DashboardMedecin = ({ user, onLogout }) => {
  const [activeMenu, setActiveMenu] = useState('tableauDeBord');

  const menuItems = [
    { key: 'tableauDeBord', label: 'Tableau de Bord' },
    { key: 'casAssignes', label: 'Cas Assignés' }
  ];

  const renderContent = () => {
    switch (activeMenu) {
      case 'tableauDeBord':
        return <h2>Bienvenue Docteur</h2>;
      case 'casAssignes':
        return <h2>Liste des cas assignés</h2>;
      default:
        return <h2>Contenu non disponible</h2>;
    }
  };

  return (
    <>
      <div className="dashboard-container">
        <aside className="sidebar">
          <div className="sidebar-header">
            <h1>Médecin</h1>
          </div>
          <ul className="menu-list">
            {menuItems.map(menu => (
              <li
                key={menu.key}
                className={activeMenu === menu.key ? 'active' : ''}
                onClick={() => setActiveMenu(menu.key)}
              >
                {menu.label}
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

export default DashboardMedecin;
