import React from 'react';
import { Link } from 'react-router-dom';
import Logo from '../../assets/images/Logo.png';
import '../../assets/css/NavBar.css';

const NavBar = () => {
  const isLoggedIn = localStorage.getItem('token');

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
              <Link className="nav-link" to="/assistance">Assistance</Link>
            </li>
            <li className="nav-item">
              <Link className="nav-link" to="/empreinte-carbone">Calculer votre empreinte carbone</Link>
            </li>
            <li className="nav-item">
              <Link className="nav-link" to="/AssistanceEtSinistre">Assistance et Sinistre</Link>
            </li>
            <li className="nav-item">
              <Link className="nav-link" to="/contact">Contact</Link>
            </li>
          </ul>

          {isLoggedIn ? (
            <Link className="btn btn-outline-success rounded-pill px-4" to="/dashboard">
              Espace Client
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