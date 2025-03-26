import React from 'react';
import gens from '../../assets/images/gens.png';
import assuranceActive from '../../assets/images/assurance_active.png';
import lesPartenaires from '../../assets/images/les-partenaires.png';
import desPays from '../../assets/images/des-pays.png';

const AchievementItem = ({ imgSrc, altText, count, description }) => (
  <div className="col-12 col-sm-6 d-flex align-items-center mb-3">
    <img className="img-fluid me-3" src={imgSrc} alt={altText} />
    <div>
      <h3><b>{count}</b></h3>
      <p>{description}</p>
    </div>
  </div>
);

const AchievementsSection = () => (
  <section className="achievements-section py-5">
    <div className="container">
      <div className="row">
        <div className="col-12 col-md-6">
          <h3>Nos Engagements
            <div className="text-primary">Pour un avenir sûr et durable</div>
          </h3>
          <p>Chez AssurMob, nous offrons des solutions d’assurance sur mesure, tout en nous engageant à préserver l'environnement pour un avenir plus durable.</p>
        </div>
        <div className="col-12 col-md-6">
          <div className="row">
            <AchievementItem imgSrc={gens} altText="members" count="5 000+" description="Membres satisfaits" />
            <AchievementItem imgSrc={assuranceActive} altText="active" count="3 000+" description="Assurances actives" />
            <AchievementItem imgSrc={lesPartenaires} altText="partners" count="50+" description="Partenaires de confiance" />
            <AchievementItem imgSrc={desPays} altText="countries" count="10+" description="Pays couverts" />
          </div>
        </div>
      </div>
    </div>
  </section>
);

export default AchievementsSection;