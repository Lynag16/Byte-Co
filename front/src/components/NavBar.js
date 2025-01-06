import React from 'react';
import { Link } from 'react-router-dom';
import Logo from '../assets/images/Logo.png';
import '../assets/css/NavBar.css';

const NavBar = () => {
  return (
    <nav className="navbar navbar-expand-lg navbar-light bg-white shadow-sm py-3">
      <div className="container">
        {/* Logo */}
        <Link className="navbar-brand d-flex align-items-center fw-bold text-primary" to="/">
          <img
            src={Logo}
            alt="Logo"
            style={{ width: '50px', marginRight: '10px' }}
          />
          <span className="fs-4">AssurMob</span>
        </Link>

        {/* Toggler Button */}
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

        {/* Navigation Links */}
        <div className="collapse navbar-collapse" id="navbarNav">
          <ul className="navbar-nav mx-auto mb-2 mb-lg-0">
            <li className="nav-item">
              <Link className="nav-link text-secondary fw-semibold" to="/">
                Accueil
              </Link>
            </li>
            <li className="nav-item">
              <Link className="nav-link text-secondary fw-semibold" to="/services">
                Services
              </Link>
            </li>
            <li className="nav-item">
              <Link className="nav-link text-secondary fw-semibold" to="/devis">
                Devis
              </Link>
            </li>
            <li className="nav-item">
              <Link className="nav-link text-secondary fw-semibold" to="/offres">
                Offres
              </Link>
            </li>
            <li className="nav-item">
              <Link className="nav-link text-secondary fw-semibold" to="/aide">
                Aide
              </Link>
            </li>
            <li className="nav-item">
              <Link className="nav-link text-secondary fw-semibold" to="/faq">
                FAQ
              </Link>
            </li>
          </ul>

          {/* Buttons */}
          <div className="d-flex gap-2">
            <Link className="btn btn-outline-primary rounded-pill px-4" to="/login">
              Se connecter
            </Link>
            <Link className="btn btn-primary rounded-pill px-4" to="/signup">
              S'inscrire
            </Link>
          </div>
        </div>
      </div>
    </nav>
  );
};

export default NavBar;
