import React from 'react';
import assistance from '../../assets/images/assistance.jpg';
import voyages from '../../assets/images/voyages.jpg';
import ecologique from '../../assets/images/ecologique.jpg';
import '../../assets/css/WhySection.css';

const CustomCard = ({ imgSrc, imgAlt, title, linkText }) => (
  <div className="col">
    <div className="custom-card">
      <img src={imgSrc} className="img-fluid" alt={imgAlt} />
      <div className="custom-overlay">
        <h3>{title}</h3>
        <a href="#" className="read-more">{linkText} →</a>
      </div>
    </div>
  </div>
);

const WhySection = () => (
  <section id="why-section" className="why-section py-5">
    <div className="container text-center">
      <div className="row">
        <div className="col-12">
          <h2>Pourquoi choisir AssurMob ?</h2>
          <p className="mx-auto" style={{ maxWidth: '600px' }}>
            Chez AssurMob, nous nous engageons à vous offrir des solutions d’assurance de qualité, adaptées à vos besoins.
            Découvrez les avantages de notre service pour voyager en toute sérénité.
          </p>
        </div>
      </div>
      <div className="row row-cols-1 row-cols-md-2 row-cols-lg-3 g-4">
        <CustomCard imgSrc={assistance} imgAlt="Assistance mondiale" title="Assistance mondiale 24/7" linkText="En savoir plus" />
        <CustomCard imgSrc={voyages} imgAlt="Adaptée à tous les voyages" title="Adaptée à tous les types de voyages" linkText="En savoir plus" />
        <CustomCard imgSrc={ecologique} imgAlt="Engagement écologique" title="Engagement écologique" linkText="En savoir plus" />
      </div>
    </div>
  </section>
);

export default WhySection;