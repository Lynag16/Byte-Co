import React from 'react';
import { useAuth } from '../auth/AuthContext';
import DashboardClient from './DashboardClient';
import DashboardGestionnaire from './DashboardGestionnaire';
import DashboardPartenaire from './DashboardPartenaire';
import DashboardUser from './DashboardUser';
import Footer from '../../components/Shared/Footer';
import { useNavigate } from 'react-router-dom';

const Dashboard = () => {
  const { user, userLogout } = useAuth();
  const navigate = useNavigate();

  const handleLogout = () => {
    userLogout();
    navigate('/login');
  };

  console.log('Rôle utilisateur :', user?.role);

  switch (user?.role?.toUpperCase()) {
    case 'CLIENT':
      return <DashboardClient user={user} onLogout={handleLogout} />;
      case 'USER':
        return <DashboardUser user={user} onLogout={handleLogout} />;
    case 'GESTIONNAIRE':
      return <DashboardGestionnaire user={user} onLogout={handleLogout} />;
    case 'PARTENAIRE':
      return <DashboardPartenaire user={user} onLogout={handleLogout} />;
    default:
      return (
        <div style={{ padding: '2rem', color: 'crimson', textAlign: 'center' }}>
          <h2>Rôle non reconnu</h2>
          <p>Votre rôle est : {user?.role}</p>
          <button onClick={handleLogout}>Se déconnecter</button>
          <Footer />
        </div> 
      );
  }
};


export default Dashboard;
