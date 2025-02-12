import React, { useEffect } from 'react';
import HeroSection from '../components/Home/HeroSection';
import PartnersSection from '../components/Home/PartnersSection';
import PromosSection from '../components/Home/PromosSection';
import AchievementsSection from '../components/Home/AchievementsSection';
import ContactSection from '../components/Home/ContactSection';
import HowSection from '../components/Home/HowSection';
import WhySection from '../components/Home/WhySection';
import TestimonialsSection from '../components/Home/TestimonialsSection';
import '../assets/css/main.css';

const HomePage = () => {
  useEffect(() => {
    document.title = 'AssurMob';
  }, []);

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
    </div>
  );
};

export default HomePage;