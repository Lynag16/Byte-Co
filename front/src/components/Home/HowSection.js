import React from 'react';
import frameImage from '../../assets/images/Frame.png';

const HowSection = () => (
  <section className="how-section py-5">
    <div className="container">
      <div className="row align-items-center">
        <div className="col-5">
          <img className="img-fluid" src={frameImage} alt="AssurMob - Comment ça marche" />
        </div>
        <div className="col-7">
          <h3 className="text-primary">AssurMob : Simple, rapide, efficace</h3>
          <p>
            AssurMob simplifie vos démarches d’assurance voyage en 3 étapes simples. Que vous soyez globe-trotter ou en déplacement professionnel, nous avons la solution pour vous !
          </p>
          <ul>
            <li><strong>Obtenez un devis :</strong> Répondez à quelques questions pour recevoir une offre personnalisée en quelques clics.</li>
            <li><strong>Choisissez votre couverture :</strong> Sélectionnez les options qui répondent à vos besoins spécifiques.</li>
            <li><strong>Voyagez en toute sérénité :</strong> Partez l'esprit tranquille avec une assistance fiable à chaque étape de votre voyage.</li>
          </ul>
        </div>
      </div>
    </div>
  </section>
);

export default HowSection;