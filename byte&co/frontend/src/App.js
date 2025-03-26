import React from 'react';
import {
  BrowserRouter as Router,
  Route,
  Routes,
  Navigate,
  useLocation
} from 'react-router-dom';
import HomePage from './pages/home/HomePage';
import Offres from './pages/offres/OffresPage';
import DeclarationSinistre from "./pages/sinistres/DeclarationSinistre";
import AssistanceEtSinistre from './pages/sinistres/AssistanceEtSinistre';
import Dashboard from './components/Dashboard/Dashboard';
import Login from './components/auth/Login';
import { AuthProvider, useAuth } from './components/auth/AuthContext';
import NavBar from './components/Shared/NavBar';
import Footer from './components/Shared/Footer';

const ProtectedRoute = ({ children }) => {
  const { userIsAuthenticated } = useAuth();
  return userIsAuthenticated() ? children : <Navigate to="/" replace />;
};

const LoginRoute = () => {
  const { userIsAuthenticated } = useAuth();
  return userIsAuthenticated() ? <Navigate to="/" replace /> : <Login />;
};

const AppLayout = ({ children }) => {
  const location = useLocation();
  const isDashboard = location.pathname === '/dashboard';

  return (
    <div className="d-flex flex-column min-vh-100">
      {!isDashboard && <NavBar />}
      <main className="flex-grow-1">{children}</main>
      {!isDashboard && <Footer />}
    </div>
  );
};

function App() {
  return (
    <AuthProvider>
      <Router>
        <AppLayout>
          <Routes>
            <Route path="/" element={<HomePage />} />
            <Route path="/login" element={<LoginRoute />} />
            <Route path="/offres" element={<Offres />} />
            <Route path="/empreinte-carbone" element={<HomePage />} />
            <Route path="/AssistanceEtSinistre" element={<AssistanceEtSinistre />} />
            <Route path="/declaration-sinistre" element={<DeclarationSinistre />} />
            <Route
              path="/dashboard"
              element={
                <ProtectedRoute>
                  <Dashboard />
                </ProtectedRoute>
              }
            />
          </Routes>
        </AppLayout>
      </Router>
    </AuthProvider>
  );
}

export default App;
