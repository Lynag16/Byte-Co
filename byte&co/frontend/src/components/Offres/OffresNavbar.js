import React, { useEffect, useState } from 'react';
import './OffresNavbar.css';

const OffresNavbar = () => {
  const [isSticky, setIsSticky] = useState(false);

  useEffect(() => {
    const navbar = document.getElementById('offres-navbar');
    const navbarOffsetTop = navbar.offsetTop;

    const handleScroll = () => {
      const scrollTop = window.pageYOffset || document.documentElement.scrollTop;

      if (scrollTop >= navbarOffsetTop) {
        setIsSticky(true);
      } else {
        setIsSticky(false);
      }
    };

    window.addEventListener('scroll', handleScroll);

    return () => {
      window.removeEventListener('scroll', handleScroll);
    };
  }, []);

  return (
    <nav id="offres-navbar" className={`offres-navbar ${isSticky ? 'sticky' : ''}`}>
      <ul>
        <li><a href="#voyage">Voyage</a></li>
        <li><a href="#sante">Santé</a></li>
        <li><a href="#pro">Déplacement Pro</a></li>
        <li><a href="#personnalise">Offre Personnalisée</a></li>
      </ul>
    </nav>
  );
};

export default OffresNavbar;
