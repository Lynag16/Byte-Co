import React from 'react';

const ContactSection = () => (
  <section className="contact-section py-5">
    <div className="container">
      <div className="row justify-content-center">
        <div className="col-8 text-center">
          <h2>Recevez nos conseils et offres exclusives pour vos voyages !</h2>
          <form className="mt-4">
            <div className="mb-3">
              <input type="email" className="form-control email-input" placeholder="Entrez votre adresse email" aria-label="Email" required />
            </div>
            <div>
              <button className="btn btn-primary text-white" type="submit">S'abonner</button>
            </div>
          </form>
        </div>
      </div>
    </div>
  </section>
);

export default ContactSection;