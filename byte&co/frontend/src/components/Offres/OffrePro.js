import React, { useState } from 'react';
import proBusiness from '../../assets/images/offres/pro-business.jpg';
import proExecutive from '../../assets/images/offres/pro-executive.jpg';
import proPrestige from '../../assets/images/offres/pro-prestige.jpg';
import './OffreSection.css';

const OffrePro = () => {
  const [isTableVisible, setIsTableVisible] = useState(false);

  const toggleTableVisibility = () => {
    setIsTableVisible(!isTableVisible);
  };

  return (
    <section id="pro" className="offre-section">
      <h2 className="section-title">Offre Déplacement Professionnel</h2>
      <p className="section-subtitle">Garantissez la continuité de vos activités professionnelles.</p>

      <div className="offers-container">
        <div className="offers-card">
          <img src={proBusiness} alt="Pro Business" className="offers-image" />
          <h3>Pro Business</h3>
          <ul>
            <li>Indemnisation en cas de retard/annulation</li>
            <li>Couverture des équipements professionnels</li>
            <li>Assistance administrative</li>
          </ul>
          <button className="btn-primary">Souscrire</button>
        </div>

        <div className="offers-card">
          <img src={proExecutive} alt="Pro Executive" className="offers-image" />
          <h3>Pro Executive</h3>
          <ul>
            <li>Assistance juridique internationale</li>
            <li>Indemnisation en cas de retard/annulation</li>
          </ul>
          <button className="btn-primary">Souscrire</button>
        </div>

        <div className="offers-card">
          <img src={proPrestige} alt="Pro Prestige" className="offers-image" />
          <h3>Pro Prestige</h3>
          <ul>
            <li>Accès aux salons VIP</li>
            <li>Compensation carbone incluse</li>
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
                <th>Pro Business</th>
                <th>Pro Executive</th>
                <th>Pro Prestige</th>
              </tr>
            </thead>
            <tbody>
              <tr>
                <td>Indemnisation en cas de retard/annulation</td>
                <td>✔</td>
                <td>✔</td>
                <td>✔</td>
              </tr>
              <tr>
                <td>Accès aux salons VIP</td>
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

export default OffrePro;
