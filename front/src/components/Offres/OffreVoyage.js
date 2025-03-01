import React, { useState } from 'react';
import './OffreSection.css';
import voyageEssentielle from '../../assets/images/offres/voyage-essentielle.jpg';
import voyageConfort from '../../assets/images/offres/voyage-confort.jpg';
import voyagePremium from '../../assets/images/offres/voyage-premium.jpg';

const OffreVoyage = () => {
  const [isTableVisible, setIsTableVisible] = useState(false);

  const toggleTableVisibility = () => {
    setIsTableVisible(!isTableVisible);
  };

  return (
    <section id="voyage" className="offre-section">
      <h2 className="section-title">Offre Voyage</h2>
      <p className="section-subtitle">
        Protégez vos déplacements partout dans le monde avec des solutions adaptées à votre façon de voyager.
      </p>

      <div className="offers-container">
        <div className="offers-card">
          <img src={voyageEssentielle} alt="Voyage Essentielle" className="offers-image" />
          <h3>Essentielle</h3>
          <ul>
            <li>Frais médicaux d'urgence</li>
            <li>Assistance rapatriement</li>
            <li>Perte ou vol de bagages</li>
          </ul>
          <button className="btn-primary">Souscrire</button>
        </div>

        <div className="offers-card">
          <img src={voyageConfort} alt="Voyage Confort" className="offers-image" />
          <h3>Confort</h3>
          <ul>
            <li>Garantie annulation de voyage</li>
            <li>Indemnisation des retards de transport</li>
            <li>Couverture des activités sportives</li>
          </ul>
          <button className="btn-primary">Souscrire</button>
        </div>

        <div className="offers-card">
          <img src={voyagePremium} alt="Voyage Premium" className="offers-image" />
          <h3>Premium</h3>
          <ul>
            <li>Accès à un service médical 24/7</li>
            <li>Assistance personnalisée en cas d’incident</li>
            <li>Compensation carbone incluse</li>
          </ul>
          <button className="btn-primary">Souscrire</button>
        </div>
      </div>

      <section className="comparateur-section">
        <div
          className="comparateur-header"
          onClick={toggleTableVisibility}
          role="button"
          aria-expanded={isTableVisible}
        >
          <h2>Les garanties</h2>
          <span className="chevron-icon">{isTableVisible ? "▲" : "▼"}</span>
        </div>

        {isTableVisible && (
          <table className="comparateur-table">
            <thead>
              <tr>
                <th>Options</th>
                <th>Essentielle</th>
                <th>Confort</th>
                <th>Premium</th>
              </tr>
            </thead>
            <tbody>
              <tr>
                <td>Frais médicaux d'urgence</td>
                <td>✔</td>
                <td>✔</td>
                <td>✔</td>
              </tr>
              <tr>
                <td>Assistance rapatriement</td>
                <td>✔</td>
                <td>✔</td>
                <td>✔</td>
              </tr>
              <tr>
                <td>Garantie annulation de voyage</td>
                <td>-</td>
                <td>✔</td>
                <td>✔</td>
              </tr>
              <tr>
                <td>Compensation carbone</td>
                <td>-</td>
                <td>-</td>
                <td>✔</td>
              </tr>
            </tbody>
          </table>
        )}
      </section>
    </section>
  );
};

export default OffreVoyage;