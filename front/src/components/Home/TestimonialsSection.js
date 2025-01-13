import React from 'react';

const TestimonialCard = ({ text, author }) => (
  <div className="col-md-4">
    <div className="testimonial-card p-4">
      <p>{text}</p>
      <h5>{author}</h5>
    </div>
  </div>
);

const TestimonialsSection = () => (
  <section className="testimonials-section py-5">
    <div className="container">
      <div className="row">
        <div className="col-12 text-center">
          <h2>Ce que disent nos clients</h2>
          <p>Découvrez les témoignages de nos clients satisfaits.</p>
        </div>
      </div>
      <div className="row">
        <TestimonialCard text="AssurMob m'a permis de voyager en toute sérénité. Leur service client est exceptionnel !" author="- Jean, 29 ans" />
        <TestimonialCard text="Une couverture complète et des tarifs compétitifs. Je recommande vivement AssurMob." author="- Karim, 41 ans" />
        <TestimonialCard text="Leur assistance 24/7 m'a sauvé lors de mon voyage à l'étranger. Merci AssurMob !" author="- Albert, 72 ans" />
      </div>
    </div>
  </section>
);

export default TestimonialsSection;