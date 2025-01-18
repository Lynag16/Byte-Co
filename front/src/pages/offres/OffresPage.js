import React from 'react';
import OffreVoyage from '../../components/Offres/OffreVoyage';
import OffreSante from '../../components/Offres/OffreSante';
import OffrePro from '../../components/Offres/OffrePro';
import OffrePersonnalisee from '../../components/Offres/OffrePersonnalisee';
import OffresSlide from '../../components/Offres/OffresSlide';
import OffresNavbar from '../../components/Offres/OffresNavbar';

const OffresPage = () => {
  return (
    <div className="offres-page">
      <h1>Nos Offres d'Assurance et d'Assistance</h1>
      <OffresSlide />
      <OffresNavbar />
      <OffreVoyage />
      <OffreSante />
      <OffrePro />
      <OffrePersonnalisee />
    </div>
  );
};

export default OffresPage;