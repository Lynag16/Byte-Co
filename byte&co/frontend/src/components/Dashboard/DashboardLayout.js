import React, { useState } from 'react';
import './Dashboard.css';
import Footer from '../../components/Shared/Footer';
import Logo from '../../assets/images/Logo.png';

const DashboardLayout = ({ user, onLogout, menuItems, renderContent }) => {
  const [activeMenu, setActiveMenu] = useState(menuItems[0]?.key || '');

  return (
    <>
      <div className="dashboard-container">
        <aside className="sidebar">
          <div className="sidebar-header">
          <img src={Logo} alt="Logo AssurMob" className="sidebar-logo" />
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
            <h2></h2>
            <button onClick={onLogout}>Déconnexion</button>
          </header>
          <section className="content-area">{renderContent(activeMenu)}</section>
        </main>
      </div>
      <Footer />
    </>
  );
};

export default DashboardLayout;