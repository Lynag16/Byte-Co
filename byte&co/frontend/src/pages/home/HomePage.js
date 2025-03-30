import React, { useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { useAuth } from '../../components/auth/AuthContext';
import HeroSection from '../../components/Home/HeroSection';
import PartnersSection from '../../components/Home/PartnersSection';
import PromosSection from '../../components/Home/PromosSection';
import AchievementsSection from '../../components/Home/AchievementsSection';
import ContactSection from '../../components/Home/ContactSection';
import HowSection from '../../components/Home/HowSection';
import WhySection from '../../components/Home/WhySection';
import OffersSection from '../../components/Home/OffersSection';
import TestimonialsSection from '../../components/Home/TestimonialsSection';
import '../../assets/css/main.css';
import './HomePage.css';

const HomePage = () => {
  const { userIsAuthenticated } = useAuth();
  const navigate = useNavigate();

  useEffect(() => {
    document.title = 'AssurMob';

    // si connecté ET revient depuis le login => on reste ici et on clean l'historique
    if (userIsAuthenticated() && window.history.state?.usr === '/login') {
      navigate('/', { replace: true });
    }
  }, [userIsAuthenticated, navigate]);

  return (
    <div>
      <HeroSection />
      <PartnersSection />
      <PromosSection />
      <AchievementsSection />
      <ContactSection />
      <HowSection />
      <WhySection />
      <TestimonialsSection />
      <OffersSection />
    </div>
  );
};

export default HomePage;
