import React from 'react';
import './OffresSlide.css';
import offreImage1 from '../../assets/images/image1.jpg';

const OffresSlide = () => {
  return (
    <section
      className="fullwidth-slide"
      style={{ backgroundImage: `url(${offreImage1})` }}
    >
      <div className="overlay">
        <h1>Découvrez toutes nos assurances</h1>
      </div>
    </section>
  );
};

export default OffresSlide;
