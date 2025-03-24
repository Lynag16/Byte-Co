import React, { useState } from 'react';
import santeBasique from '../../assets/images/offres/sante-basique.jpg';
import santeSerenite from '../../assets/images/offres/sante-serenite.jpg';
import santePremium from '../../assets/images/offres/sante-premium.jpg';
import './OffreSection.css';

const OffreSante = () => {
  const [isTableVisible, setIsTableVisible] = useState(false);

  const toggleTableVisibility = () => {
    setIsTableVisible(!isTableVisible);
  };

  return (
    <section id="sante" className="offre-section">
      <h2 className="section-title">Offre Santé</h2>
      <p className="section-subtitle">Une protection complète pour votre santé en déplacement.</p>

      <div className="offers-container">
        <div className="offers-card">
          <img src={santeBasique} alt="Santé Basique" className="offers-image" />
          <h3>Basique</h3>
          <ul>
            <li>Conseils médicaux à distance</li>
            <li>Prise en charge des frais médicaux d'urgence</li>
          </ul>
          <button className="btn-primary">Souscrire</button>
        </div>

        <div className="offers-card">
          <img src={santeSerenite} alt="Santé Sérénité" className="offers-image" />
          <h3>Sérénité</h3>
          <ul>
            <li>Téléconsultation illimitée</li>
            <li>Suivi médical personnalisé</li>
          </ul>
          <button className="btn-primary">Souscrire</button>
        </div>

        <div className="offers-card">
          <img src={santePremium} alt="Santé Premium" className="offers-image" />
          <h3>Premium</h3>
          <ul>
            <li>Assistance psychologique</li>
            <li>Rapatriement médical prioritaire</li>
          </ul>
          <button className="btn-primary">Souscrire</button>
        </div>
      </div>

      <section className="comparateur-section">
        <div className="comparateur-header" onClick={toggleTableVisibility}>
          <h2>Les garanties</h2>
          <span className="chevron-icon">{isTableVisible ? "▲" : "▼"}</span>
        </div>

        {isTableVisible && (
          <table className="comparateur-table">
            <thead>
              <tr>
                <th>Garanties</th>
                <th>Basique</th>
                <th>Sérénité</th>
                <th>Premium</th>
              </tr>
            </thead>
            <tbody>
              <tr>
                <td>Conseils médicaux à distance</td>
                <td>✔</td>
                <td>✔</td>
                <td>✔</td>
              </tr>
              <tr>
                <td>Téléconsultation illimitée</td>
                <td>-</td>
                <td>✔</td>
                <td>✔</td>
              </tr>
              <tr>
                <td>Rapatriement médical prioritaire</td>
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

export default OffreSante;
