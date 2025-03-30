import React, { useState, useEffect } from 'react';
import '../../assets/css/HeroSection.css';
import image1 from '../../assets/images/image1.jpg';
import image2 from '../../assets/images/image2.jpg';
import image3 from '../../assets/images/image3.jpg';

const images = [image1, image2, image3];

const HeroSection = () => {
  const [currentImageIndex, setCurrentImageIndex] = useState(0);

  useEffect(() => {
    const interval = setInterval(() => {
      setCurrentImageIndex((prevIndex) =>
        prevIndex === images.length - 1 ? 0 : prevIndex + 1
      );
    }, 3000);

    return () => clearInterval(interval);
  }, []);

  return (
    <section
      className="hero-section"
      style={{ backgroundImage: `url(${images[currentImageIndex]})` }}
    >
      <div className="carousel-overlay">
        <h1>Voyagez serein</h1>
        <h2 className="text-primary">Avec une assurance sur mesure</h2>
        <p>AssurMob vous protège où que vous soyez.</p>
        <a href="#why-section" className="btn btn-primary text-white">En savoir plus</a>
      </div>
    </section>
  );
};

export default HeroSection;