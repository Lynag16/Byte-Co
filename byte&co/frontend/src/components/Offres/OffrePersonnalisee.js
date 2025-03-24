import React from 'react';
import personnaliseImage from '../../assets/images/offres/personnalise.jpg';
import './OffreSection.css';

const OffrePersonnalise = () => {
  return (
    <section id="personnalise" className="offre-section">
      <h2 className="section-title">Offre Personnalisée</h2>
      <p className="section-subtitle">Composez votre assurance selon vos besoins spécifiques.</p>

      <div className="offers-container">
        <div className="offers-card">
          <img src={personnaliseImage} alt="Offre Personnalisée" className="offers-image" />
          <h3>Personnalisez votre offre</h3>
          <ul>
            <li>Assistance médicale 24/7</li>
            <li>Rapatriement sanitaire</li>
            <li>Compensation carbone</li>
            <li>Annulation de voyage</li>
            <li>Suivi médical personnalisé</li>
            <li>Assistance psychologique</li>
          </ul>
          <button className="btn-primary">Créer mon offre</button>
        </div>
      </div>
    </section>
  );
};

export default OffrePersonnalise;
