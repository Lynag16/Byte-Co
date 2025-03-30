import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import Logo from '../../assets/images/Logo.png';
import '../../assets/css/NavBar.css';
import { useAuth } from '../auth/AuthContext';

const NavBar = () => {
  const { user, userIsAuthenticated } = useAuth();
  const [isAuthenticated, setIsAuthenticated] = useState(false);

  // Permet d’éviter les décalages si l’état vient du localStorage/sessionStorage
  useEffect(() => {
    setIsAuthenticated(userIsAuthenticated());
  }, [user]);

  return (
    <nav className="navbar fixed-top navbar-expand-lg bg-white shadow-sm py-3">
      <div className="container">
        <Link className="navbar-brand d-flex align-items-center fw-bold text-success" to="/">
          <img src={Logo} alt="Logo AssurMob" style={{ width: '50px', marginRight: '10px' }} />
          <span className="fs-4">AssurMob</span>
        </Link>

        <button
          className="navbar-toggler"
          type="button"
          data-bs-toggle="collapse"
          data-bs-target="#navbarNav"
          aria-controls="navbarNav"
          aria-expanded="false"
          aria-label="Toggle navigation"
        >
          <span className="navbar-toggler-icon"></span>
        </button>

        <div className="collapse navbar-collapse" id="navbarNav">
          <ul className="navbar-nav mx-auto">
            <li className="nav-item">
              <Link className="nav-link" to="/offres">Nos Offres</Link>
            </li>
            <li className="nav-item">
              <Link className="nav-link" to="/empreinte-carbone">Calculer votre empreinte carbone</Link>
            </li>
            <li className="nav-item">
              <Link className="nav-link" to="/AssistanceEtSinistre">Assistance et Sinistre</Link>
            </li>
          </ul>

          {isAuthenticated ? (
            <Link className="btn btn-outline-success rounded-pill px-4" to="/dashboard">
              Espace personnel
            </Link>
          ) : (
            <Link className="btn btn-success rounded-pill px-4" to="/login">
              Se connecter
            </Link>
          )}
        </div>
      </div>
    </nav>
  );
};

export default NavBar;
