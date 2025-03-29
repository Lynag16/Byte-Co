import React from 'react';
import promotion from '../../assets/images/promotion.png';

const PromosSection = () => (
  <section className="py-5">
    <div className="container">
      <div className="row align-items-center">
        <div className="col-5">
          <img className="img-fluid" src={promotion} alt="Offres estivales AssurMob" />
        </div>
        <div className="col-7">
          <h3 className="text-primary">Préparez vos voyages d'été avec 10% de réduction !</h3>
          <p>
            Cet été, partez l'esprit tranquille ! Bénéficiez d'une réduction exclusive de
            <strong> 10%</strong> sur toutes nos assurances voyage pour toute souscription avant le
            <strong> 30 juin</strong>.
            AssurMob vous accompagne pour des vacances en toute sérénité, où que vous soyez.
          </p>
          <a href="" className="btn btn-primary text-white">Je profite de l'offre</a>
        </div>
      </div>
    </div>
  </section>
);

export default PromosSection;