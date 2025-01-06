import React from 'react';
import partenairesLogos from '../../assets/images/Partenaires Logos.png';
import assurance from '../../assets/images/assurance.png';
import serviceClients from '../../assets/images/service-clients.png';
import distributionMondiale from '../../assets/images/distribution-mondiale.png';
import environmentalProtection from '../../assets/images/environmental-protection.png';
import '../../assets/css/PartnersSection.css';

const PartnersSection = () => {
  return (
    <section className="partners-section py-5">
      <div className="container">
        <div className="row">
          <div className="col-12 text-center">
            <h2 className="fade-in">Nos partenaires de confiance</h2>
            <p className="text-muted fade-in">
              Nous collaborons avec des leaders de l'industrie pour vous offrir des solutions d’assurance de qualité.
            </p>
            <img className="img-fluid fade-in" src={partenairesLogos} alt="Logos des partenaires"/>
          </div>
        </div>
        <div className="row text-center py-5 fade-in">
          <h2>Une solution pensée pour tous vos besoins</h2>
          <p>Pourquoi choisir AssurMob ?</p>
        </div>

        <div className="row text-center py-5 points-cles">
          <div className="col-sm-12 col-md-3 fade-in">
            <img className="img-fluid mb-3 img-hover-zoom" src={assurance} alt="Assurance voyage" />
            <h3 className="title">Assurance Voyage Complète</h3>
            <p className="description">Bénéficiez d’une couverture personnalisée pour tous vos déplacements, qu’ils soient professionnels ou personnels.</p>
          </div>

          <div className="col-sm-12 col-md-3 fade-in">
            <img className="img-fluid mb-3 img-hover-zoom" src={serviceClients} alt="Assistance 24/7" />
            <h3 className="title">Assistance 24/7</h3>
            <p className="description">Notre équipe dédiée est disponible à tout moment pour répondre à vos urgences et imprévus.</p>
          </div>

          <div className="col-sm-12 col-md-3 fade-in">
            <img className="img-fluid mb-3 img-hover-zoom" src={distributionMondiale} alt="Couverture mondiale" />
            <h3 className="title">Couverture Mondiale</h3>
            <p className="description">Voyagez en toute sérénité grâce à une protection étendue à l'international.</p>
          </div>

          <div className="col-sm-12 col-md-3 fade-in">
            <img className="img-fluid mb-3 img-hover-zoom" src={environmentalProtection} alt="Engagement environnemental" />
            <h3 className="title">Engagement Environnemental</h3>
            <p className="description">Réduisez votre empreinte carbone grâce à nos initiatives de compensation écologiques.</p>
          </div>
        </div>
      </div>
    </section>
  );
};

export default PartnersSection;
