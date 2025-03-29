import React, { useState } from 'react';
import axios from 'axios';
import Snackbar from '@mui/material/Snackbar';
import Alert from '@mui/material/Alert';
import { useAuth } from '../../../auth/AuthContext';
import '../../../Dashboard/sinistres/DeclarationSinistre.css';

const IncidentMedicalForm = () => {
  const { user } = useAuth();

  const [formVisible, setFormVisible] = useState(true);
  const [form, setForm] = useState({
    description: '',
    symptomes: '',
    typeIntervention: '',
    coutIntervention: '',
    dossierMedical: null,
  });

  const [errors, setErrors] = useState({});
  const [snackbar, setSnackbar] = useState({ open: false, success: false, message: '' });

  const today = new Date();
  const todayFormatted = today.toLocaleDateString('fr-FR');
  const todayISO = today.toISOString().split('T')[0];

  const validate = () => {
    const newErrors = {};
    if (!form.description.trim()) newErrors.description = 'La description est requise';
    if (!form.symptomes.trim()) newErrors.symptomes = 'Les symptômes sont requis';
    if (!form.typeIntervention) newErrors.typeIntervention = 'Le type d’intervention est requis';
    if (!form.coutIntervention || isNaN(form.coutIntervention)) newErrors.coutIntervention = 'Le coût doit être un nombre';
    setErrors(newErrors);
    return Object.keys(newErrors).length === 0;
  };

  const handleChange = (e) => {
    const { name, value, files } = e.target;
    setForm({ ...form, [name]: files ? files[0] : value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (!validate()) return;

    const dto = {
      description: form.description,
      dateDeclaration: todayISO,
      symptomes: form.symptomes,
      typeIntervention: form.typeIntervention,
      coutIntervention: parseFloat(form.coutIntervention),
      typeSinistre: 'INCIDENT_MEDICAL',
    };

    const formData = new FormData();
    formData.append('dto', new Blob([JSON.stringify(dto)], { type: 'application/json' }));
    if (form.dossierMedical) {
      formData.append('dossierMedical', form.dossierMedical);
    }

    try {
      await axios.post('http://localhost:8085/api/sinistres/incident-medical', formData, {
        headers: {
          'Content-Type': 'multipart/form-data',
          Authorization: `Bearer ${user?.token}`,
        },
      });
      setSnackbar({
        open: true,
        success: true,
        message: 'Déclaration enregistrée avec succès. Nous vous recontacterons si besoin.',
      });
      setFormVisible(false);
    } catch (error) {
      setSnackbar({
        open: true,
        success: false,
        message: 'Erreur lors de la déclaration. Veuillez réessayer.',
      });
    }
  };

  const handleCloseSnackbar = () => {
    setSnackbar({ ...snackbar, open: false });
  };

  return (
    <>
      {formVisible && (
        <form onSubmit={handleSubmit} className="declaration-sinistre-form" autoComplete="off" lang="fr">
          <div className="form-section">
            <label htmlFor="description">Description du sinistre</label>
            <textarea name="description" placeholder="Décrivez brièvement la situation" value={form.description} onChange={handleChange} required />
            {errors.description && <span className="error-message">{errors.description}</span>}
          </div>

          <div className="form-section">
            <label>Date de déclaration</label>
            <input type="text" value={todayFormatted} disabled />
          </div>

          <div className="form-section">
            <label htmlFor="symptomes">Symptômes</label>
            <input type="text" name="symptomes" placeholder="Ex : fièvre, nausées, douleurs" value={form.symptomes} onChange={handleChange} required />
            {errors.symptomes && <span className="error-message">{errors.symptomes}</span>}
          </div>

          <div className="form-section">
            <label htmlFor="typeIntervention">Type d’intervention</label>
            <select name="typeIntervention" value={form.typeIntervention} onChange={handleChange} required>
              <option value="">-- Sélectionnez --</option>
              <option value="CONSULTATION">Consultation</option>
              <option value="HOSPITALISATION">Hospitalisation</option>
              <option value="URGENCE">Urgence</option>
              <option value="AUTRE">Autre</option>
            </select>
            {errors.typeIntervention && <span className="error-message">{errors.typeIntervention}</span>}
          </div>

          <div className="form-section">
            <label htmlFor="coutIntervention">Coût de l’intervention (€)</label>
            <input type="number" name="coutIntervention" placeholder="Montant en euros" value={form.coutIntervention} onChange={handleChange} required />
            {errors.coutIntervention && <span className="error-message">{errors.coutIntervention}</span>}
          </div>

          <div className="form-section">
            <label htmlFor="dossierMedical">Joindre un dossier médical (PDF)</label>
            <input type="file" name="dossierMedical" accept=".pdf" onChange={handleChange} className="custom-file-input" />
          </div>

          <button type="submit" className="submit-button">Soumettre la déclaration</button>
        </form>
      )}

      <Snackbar open={snackbar.open} autoHideDuration={6000} onClose={handleCloseSnackbar} anchorOrigin={{ vertical: 'top', horizontal: 'center' }}>
        <Alert onClose={handleCloseSnackbar} severity={snackbar.success ? 'success' : 'error'} variant="filled" sx={{ width: '100%' }}>
          {snackbar.message}
        </Alert>
      </Snackbar>
    </>
  );
};

export default IncidentMedicalForm;
