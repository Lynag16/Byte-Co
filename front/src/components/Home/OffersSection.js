import React from 'react';
import { useNavigate } from 'react-router-dom';
import voyageImage from '../../assets/images/offre-voyage.jpg';
import santeImage from '../../assets/images/offre-sante.jpg';
import proImage from '../../assets/images/offre-pro.jpg';
import persoImage from '../../assets/images/offre-personnalisee.jpg';
import '../../assets/css/OffresSection.css';

const OffersSection = () => {
  const navigate = useNavigate();

  const handleLearnMore = (offreType) => {
    navigate(`/offres/${offreType}`);
  };

  return (
    <section className="offers-section">
      <h2 className="section-title">Nos Solutions d'Assurance</h2>
      <div className="offers-container">
        <div className="offers-card">
          <img src={voyageImage} alt="Offre Voyage" className="offers-image" />
          <h3>Voyage</h3>
          <p>Assurance et assistance pour les voyages personnels.</p>
          <button
            className="btn-primary"
            onClick={() => handleLearnMore('voyage')}
          >
            En savoir plus
          </button>
        </div>
        <div className="offers-card">
          <img src={santeImage} alt="Offre Santé" className="offers-image" />
          <h3>Santé</h3>
          <p>Assurance santé et services médicaux.</p>
          <button
            className="btn-primary"
            onClick={() => handleLearnMore('sante')}
          >
            En savoir plus
          </button>
        </div>
        <div className="offers-card">
          <img src={proImage} alt="Offre Déplacement Professionnel" className="offers-image" />
          <h3>Déplacement Pro</h3>
          <p>Pour les voyages professionnels avec gestion des imprévus.</p>
          <button
            className="btn-primary"
            onClick={() => handleLearnMore('professionnel')}
          >
            En savoir plus
          </button>
        </div>
        <div className="offers-card">
          <img src={persoImage} alt="Offre Personnalisée" className="offers-image" />
          <h3>Formule Personnalisée</h3>
          <p>Choisissez des risques selon vos besoins.</p>
          <button
            className="btn-primary"
            onClick={() => handleLearnMore('personnalisee')}
          >
            En savoir plus
          </button>
        </div>
      </div>
    </section>
  );
};

export default OffersSection;
