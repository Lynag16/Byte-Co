import React from "react";
import Logo from '../assets/images/Logo.png';
import 'bootstrap-icons/font/bootstrap-icons.css';

const Footer = () => {
  return (
    <footer className="footer bg-dark text-light py-5">
      <div className="container">
        <div className="row">

          {/* Logo et Copyright */}
          <div className="col-12 col-md-4 text-center text-md-start mb-4 mb-md-0">
            <h3 className="fw-bold mb-3">AssurMob</h3>
            <img
              src={Logo}
              alt="Logo AssurMob"
              style={{ width: "50px", marginBottom: "10px" }}
            />
            <p className="small mb-0">Copyright © 2024 Byte&Co ltd.</p>
            <p className="small">All rights reserved</p>
            <div className="d-flex justify-content-center justify-content-md-start gap-2">
              <a href="#" className="text-light">
                <i className="bi bi-instagram fs-5"></i>
              </a>
              <a href="#" className="text-light">
                <i className="bi bi-globe fs-5"></i>
              </a>
              <a href="#" className="text-light">
                <i className="bi bi-twitter fs-5"></i>
              </a>
              <a href="#" className="text-light">
                <i className="bi bi-youtube fs-5"></i>
              </a>
            </div>
          </div>

          {/* Entreprise */}
          <div className="col-6 col-md-2 mb-4 mb-md-0">
            <h5 className="mb-3">Entreprise</h5>
            <ul className="list-unstyled">
              <li><a href="#" className="text-light text-decoration-none">À propos</a></li>
              <li><a href="#" className="text-light text-decoration-none">Blog</a></li>
              <li><a href="#" className="text-light text-decoration-none">Contact</a></li>
              <li><a href="#" className="text-light text-decoration-none">Politique de Confidentialité</a></li>
              <li><a href="#" className="text-light text-decoration-none">Mentions Légales</a></li>
            </ul>
          </div>

          {/* Support */}
          <div className="col-6 col-md-3 mb-4 mb-md-0">
            <h5 className="mb-3">Support</h5>
            <p className="small mb-1">Email :</p>
            <a
              href="mailto:support@assurmob.com"
              className="text-light text-decoration-none small"
            >
              support@assurmob.com
            </a>
            <p className="small mt-2 mb-1">Téléphone :</p>
            <p className="small mb-0">+33 1 23 45 67 89</p>
          </div>

          {/* Newsletter */}
          <div className="col-12 col-md-3 text-center text-md-start">
            <h5 className="mb-3">Stay up to date</h5>
            <form className="d-flex">
              <input
                type="email"
                className="form-control me-2"
                placeholder="Entrez votre adresse email"
              />
              <button className="btn btn-secondary" type="submit">
                <i className="bi bi-send"></i>
              </button>
            </form>
          </div>
        </div>
      </div>
    </footer>
  );
};

export default Footer;