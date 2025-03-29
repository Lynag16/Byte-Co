import React, { useState } from 'react';
import axios from 'axios';
import Snackbar from '@mui/material/Snackbar';
import Alert from '@mui/material/Alert';
import { useAuth } from '../../../auth/AuthContext';
import '../../../Dashboard/sinistres/DeclarationSinistre.css';

const VolForm = () => {
  const { user } = useAuth();

  const [formVisible, setFormVisible] = useState(true);
  const [form, setForm] = useState({
    description: '',
    lieuVol: '',
    descriptionObjetPerdu: '',
    valeurObjetPerdu: '',
    declarationPolice: null,
  });

  const [errors, setErrors] = useState({});
  const [snackbar, setSnackbar] = useState({ open: false, success: false, message: '' });

  const today = new Date();
  const todayFormatted = today.toLocaleDateString('fr-FR');
  const todayISO = today.toISOString().split('T')[0];

  const validate = () => {
    const newErrors = {};
    if (!form.description.trim()) newErrors.description = 'La description est requise';
    if (!form.lieuVol.trim()) newErrors.lieuVol = 'Le lieu du vol est requis';
    if (!form.descriptionObjetPerdu.trim()) newErrors.descriptionObjetPerdu = 'La description de l’objet est requise';
    if (!form.valeurObjetPerdu || isNaN(form.valeurObjetPerdu)) newErrors.valeurObjetPerdu = 'La valeur doit être un nombre';

    setErrors(newErrors);
    return Object.keys(newErrors).length === 0;
  };

  const handleChange = (e) => {
    const { name, value, files } = e.target;
    setForm({
      ...form,
      [name]: files ? files[0] : value,
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (!validate()) return;

    const dto = {
      description: form.description,
      dateDeclaration: todayISO,
      lieuVol: form.lieuVol,
      descriptionObjetPerdu: form.descriptionObjetPerdu,
      valeurObjetPerdu: parseFloat(form.valeurObjetPerdu),
      typeSinistre: 'VOL_OU_PERTE_OBJET',
    };

    const formData = new FormData();
    formData.append('dto', new Blob([JSON.stringify(dto)], { type: 'application/json' }));
    if (form.declarationPolice) {
      formData.append('declarationPolice', form.declarationPolice);
    }

    try {
      await axios.post('http://localhost:8085/api/sinistres/vol-ou-perte-objet', formData, {
        headers: {
          'Content-Type': 'multipart/form-data',
          Authorization: `Bearer ${user?.token}`,
        },
      });
      setSnackbar({
        open: true,
        success: true,
        message: 'Votre déclaration a été enregistrée. Nous vous tiendrons informé sous peu.',
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
            <textarea
              name="description"
              placeholder="Expliquez les circonstances du vol ou de la perte"
              value={form.description}
              onChange={handleChange}
              required
              autoComplete="off"
            />
            {errors.description && <span className="error-message">{errors.description}</span>}
          </div>

          <div className="form-section">
            <label>Date de déclaration</label>
            <input type="text" value={todayFormatted} disabled />
          </div>

          <div className="form-section">
            <label htmlFor="lieuVol">Lieu du vol</label>
            <input
              type="text"
              name="lieuVol"
              placeholder="Ex : Gare de Lyon, hôtel à Paris"
              value={form.lieuVol}
              onChange={handleChange}
              required
              autoComplete="off"
            />
            {errors.lieuVol && <span className="error-message">{errors.lieuVol}</span>}
          </div>

          <div className="form-section">
            <label htmlFor="descriptionObjetPerdu">Objet perdu</label>
            <input
              type="text"
              name="descriptionObjetPerdu"
              placeholder="Ex : Sac à dos noir avec ordinateur"
              value={form.descriptionObjetPerdu}
              onChange={handleChange}
              required
              autoComplete="off"
            />
            {errors.descriptionObjetPerdu && <span className="error-message">{errors.descriptionObjetPerdu}</span>}
          </div>

          <div className="form-section">
            <label htmlFor="valeurObjetPerdu">Valeur estimée (€)</label>
            <input
              type="number"
              name="valeurObjetPerdu"
              placeholder="Ex : 1200"
              value={form.valeurObjetPerdu}
              onChange={handleChange}
              required
              autoComplete="off"
            />
            {errors.valeurObjetPerdu && <span className="error-message">{errors.valeurObjetPerdu}</span>}
          </div>

          <div className="form-section">
            <label htmlFor="declarationPolice">Joindre la déclaration de police (PDF)</label>
            <input type="file" name="declarationPolice" accept=".pdf" onChange={handleChange} className="custom-file-input" />
          </div>

          <button type="submit" className="submit-button">Soumettre la déclaration</button>
        </form>
      )}

      <Snackbar
        open={snackbar.open}
        autoHideDuration={6000}
        onClose={handleCloseSnackbar}
        anchorOrigin={{ vertical: 'top', horizontal: 'center' }}
      >
        <Alert onClose={handleCloseSnackbar} severity={snackbar.success ? 'success' : 'error'} variant="filled" sx={{ width: '100%' }}>
          {snackbar.message}
        </Alert>
      </Snackbar>
    </>
  );
};

export default VolForm;
