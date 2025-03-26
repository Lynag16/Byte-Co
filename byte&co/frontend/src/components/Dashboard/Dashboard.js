import React from 'react';
import { useAuth } from '../auth/AuthContext';
import DashboardClient from './DashboardClient';
import DashboardAdmin from './DashboardAdmin';
import DashboardMedecin from './DashboardMedecin';
import DashboardPartenaire from './DashboardPartenaire';
import Footer from '../../components/Shared/Footer';
import { useNavigate } from 'react-router-dom';

const Dashboard = () => {
  const { user, userLogout } = useAuth();
  const navigate = useNavigate();

  const handleLogout = () => {
    userLogout();
    navigate('/login');
  };

  console.log('Rôle utilisateur :', user?.role); // ✅ CORRECT ici

  switch (user?.role?.toUpperCase()) {
    case 'CLIENT':
      return <DashboardClient user={user} onLogout={handleLogout} />;
    case 'ADMIN':
      return <DashboardAdmin user={user} onLogout={handleLogout} />;
    case 'MEDECIN':
      return <DashboardMedecin user={user} onLogout={handleLogout} />;
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
