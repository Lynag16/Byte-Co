import React, { useState } from 'react';
import { motion } from 'framer-motion';
import { FaCarCrash, FaShieldAlt, FaPhoneAlt, FaMedkit, FaBed } from 'react-icons/fa';
import AccidentForm from './forms/AccidentForm';
import VolForm from './forms/VolForm';
import RetardForm from './forms/RetardForm';
import IncidentMedicalForm from './forms/IncidentMedicalForm';
import ProblemeHebergementForm from './forms/ProblemeHebergementForm';
import './DeclarationSinistre.css';

const DeclarationSinistre = () => {
  const [sinistreType, setSinistreType] = useState('');
  const [formKey, setFormKey] = useState(0);

  const handleSinistreClick = (type) => {
    setSinistreType(type);
    setFormKey(prev => prev + 1);
  };

  const renderSelectedForm = () => {
    switch (sinistreType) {
      case 'accident':
        return <AccidentForm key={formKey} />;
      case 'vol':
        return <VolForm key={formKey} />;
      case 'retard':
        return <RetardForm key={formKey} />;
      case 'incident-medical':
        return <IncidentMedicalForm key={formKey} />;
      case 'probleme-hebergement':
        return <ProblemeHebergementForm key={formKey} />;
      default:
        return null;
    }
  };

  return (
    <div className="declaration-sinistre-container">
      <motion.h2
        initial={{ opacity: 0, y: -20 }}
        animate={{ opacity: 1, y: 0 }}
        transition={{ duration: 0.5 }}
        className="declaration-sinistre-title"
      >
        Déclarer un Sinistre
      </motion.h2>

      <p className="declaration-sinistre-subtitle">
        Choisissez le type de sinistre que vous souhaitez déclarer et remplissez le formulaire approprié.
      </p>

      <div className="sinistre-options">
        <div className="sinistre-option" onClick={() => handleSinistreClick('accident')}>
          <FaCarCrash className="sinistre-icon" />
          <p>Accident de route</p>
        </div>
        <div className="sinistre-option" onClick={() => handleSinistreClick('vol')}>
          <FaShieldAlt className="sinistre-icon" />
          <p>Vol ou Perte d'objet</p>
        </div>
        <div className="sinistre-option" onClick={() => handleSinistreClick('retard')}>
          <FaPhoneAlt className="sinistre-icon" />
          <p>Retard de transport</p>
        </div>
        <div className="sinistre-option" onClick={() => handleSinistreClick('incident-medical')}>
          <FaMedkit className="sinistre-icon" />
          <p>Incident médical</p>
        </div>
        <div className="sinistre-option" onClick={() => handleSinistreClick('probleme-hebergement')}>
          <FaBed className="sinistre-icon" />
          <p>Problème d'hébergement</p>
        </div>
      </div>

      {sinistreType && (
        <div className="declaration-sinistre-form">
          {renderSelectedForm()}
        </div>
      )}
    </div>
  );
};

export default DeclarationSinistre;
