import React, { useState, useEffect } from 'react';
import axios from 'axios';
import {
  Box,
  Button,
  Dialog,
  DialogTitle,
  DialogContent,
  IconButton,
  TextField,
  MenuItem,
} from '@mui/material';
import { DataGrid } from '@mui/x-data-grid';
import { Close } from '@mui/icons-material';
import { useAuth } from '../auth/AuthContext';
import { motion } from 'framer-motion';


const GestionPersonnel = () => {
  const { user } = useAuth();
  const [personnel, setPersonnel] = useState([]);
  const [partners, setPartners] = useState([]);
  const [openDialog, setOpenDialog] = useState(false);
  const [dialogType, setDialogType] = useState('');
  const [currentItem, setCurrentItem] = useState(null);

  useEffect(() => {
    if (!user?.token) return;

    const fetchPersonnel = async () => {
      try {
        const res = await axios.get('http://localhost:8081/api/personnels', {
          headers: { Authorization: `Bearer ${user.token}` },
        });
        setPersonnel(res.data.map(item => ({ ...item, id: item.idpersonnel })));
      } catch (err) {
        console.error('Erreur chargement personnels:', err);
      }
    };

    const fetchPartners = async () => {
      try {
        const res = await axios.get('http://localhost:8081/api/partenaires/', {
          headers: { Authorization: `Bearer ${user.token}` },
        });
        setPartners(res.data.map(item => ({ ...item, id: item.idPartenaire })));
      } catch (err) {
        console.error('Erreur chargement partenaires:', err);
      }
    };

    fetchPersonnel();
    fetchPartners();
  }, [user]);

  const handleOpenDialog = (type, item) => {
    setDialogType(type);
    setCurrentItem(item);
    setOpenDialog(true);
  };

  const handleCloseDialog = () => {
    setOpenDialog(false);
    setCurrentItem(null);
  };

  const handleSubmit = async () => {
    
    try {
      if (dialogType === 'personnel') {
        if (currentItem?.id) {
          const res = await axios.put(`http://localhost:8081/api/personnels/${currentItem.id}`, currentItem, {
            headers: { Authorization: `Bearer ${user.token}` },
          });
          setPersonnel(prev => prev.map(p => (p.id === currentItem.id ? { ...res.data, id: res.data.idpersonnel } : p)) );
        } else {
          const res = await axios.post('http://localhost:8081/api/personnels', currentItem, {
            headers: { Authorization: `Bearer ${user.token}` },
          });
          setPersonnel(prev => [...prev, { ...res.data, id: res.data.idpersonnel }]);
        }
      } else {
        if (currentItem?.idPartenaire) {
          const res = await axios.put(`http://localhost:8081/api/partenaires/${currentItem.idPartenaire}`, currentItem, {
            headers: { Authorization: `Bearer ${user.token}` },
          });
          setPartners(prev => prev.map(p => (p.idPartenaire === currentItem.idPartenaire ? res.data : p)));
        } else {
          const res = await axios.post('http://localhost:8081/api/partenaires', currentItem, {
            headers: { Authorization: `Bearer ${user.token}` },
          });
          setPartners(prev => [...prev, { ...res.data, id: res.data.idPartenaire }]);
        }
      }
      handleCloseDialog();
    } catch (err) {
      console.error('Erreur lors de l’enregistrement :', err);
    }
  };

  const handleDelete = async (type, item) => {
    const confirm = window.confirm('Confirmer la suppression ?');
    if (!confirm) return;

    try {
      if (type === 'personnel') {
        await axios.delete(`http://localhost:8081/api/personnels/${item.id}`, {
          headers: { Authorization: `Bearer ${user.token}` },
        });
        setPersonnel(prev => prev.filter(p => p.id !== item.id));
      } else {
        await axios.delete(`http://localhost:8081/api/partenaires/${item.idPartenaire}`, {
          headers: { Authorization: `Bearer ${user.token}` },
        });
        setPartners(prev => prev.filter(p => p.idPartenaire !== item.idPartenaire));
      }
    } catch (err) {
      console.error('Erreur suppression :', err);
    }
  };

  const columnsPersonnel = [
    { field: 'nompersonnel', headerName: 'Nom', flex: 1 },
    { field: 'prenompersonnel', headerName: 'Prénom', flex: 1 },
    { field: 'rolepersonnel', headerName: 'Rôle', flex: 1 },
    { field: 'departementpersonnel', headerName: 'Département', flex: 1 },
    { field: 'emailpersonnel', headerName: 'Email', flex: 1 },
    { field: 'telephonepersonnel', headerName: 'Téléphone', flex: 1 },
    {
      field: 'actions',
      headerName: 'Actions',
      flex: 1,
      renderCell: (params) => (
        <>
          <Button size="small" onClick={() => handleOpenDialog('personnel', params.row)}>Modifier</Button>
          <Button size="small" color="error" onClick={() => handleDelete('personnel', params.row)}>Supprimer</Button>
        </>
      ),
    },
  ];

  const columnsPartners = [
    { field: 'nomPartenaire', headerName: 'Nom', flex: 1 },
    { field: 'zonegeo', headerName: 'Zone Géographique', flex: 1 },
    { field: 'emailPartenaire', headerName: 'Email', flex: 1 },
    { field: 'telephonePartenaire', headerName: 'Téléphone', flex: 1 },
    {
      field: 'actions',
      headerName: 'Actions',
      flex: 1,
      renderCell: (params) => (
        <>
          <Button size="small" onClick={() => handleOpenDialog('partenaire', params.row)}>Modifier</Button>
          <Button size="small" color="error" onClick={() => handleDelete('partenaire', params.row)}>Supprimer</Button>
        </>
      ),
    },
  ];

  return (
    <Box>
      <Box mb={4}>
        <motion.h2
                    initial={{ opacity: 0, y: -20 }}
                    animate={{ opacity: 1, y: 0 }}
                    transition={{ duration: 0.5 }}
                    className="declaration-sinistre-title"
                >
                Gestion du personnel et partenaires
                </motion.h2>
                <Box mb={2}>
                <Box className="ajout-buttons-container">
        <Box sx={{ display: 'flex', gap: 2, mb: 2 }}>
  <Button
    variant="contained"
    color="primary"
    size="small"
    onClick={() => handleOpenDialog('personnel', {})}
  >
    Ajouter un personnel
  </Button>
  <Button
    variant="contained"
    color="secondary"
    size="small"
    onClick={() => handleOpenDialog('partenaire', {})}
  >
    Ajouter un partenaire
  </Button>
</Box>

      </Box>

      </Box>
        <DataGrid
          rows={personnel}
          columns={columnsPersonnel}
          pageSize={5}
          autoHeight
          getRowId={(row) => row.id}
        />
      </Box>

      <Box mb={4}>
        <h3>Gestion des partenaires</h3>
        <DataGrid
          rows={partners}
          columns={columnsPartners}
          pageSize={5}
          autoHeight
          getRowId={(row) => row.id}
        />
      </Box>

      <Dialog open={openDialog} onClose={handleCloseDialog} maxWidth="sm" fullWidth>
        <DialogTitle>
          {dialogType === 'personnel'
            ? currentItem?.id ? 'Modifier un personnel' : 'Ajouter un personnel'
            : currentItem?.idPartenaire ? 'Modifier un partenaire' : 'Ajouter un partenaire'}
          <IconButton
            edge="end"
            color="inherit"
            onClick={handleCloseDialog}
            sx={{ position: 'absolute', right: 8, top: 8 }}
          >
            <Close />
          </IconButton>
        </DialogTitle>
        <DialogContent>
          <TextField
            label="Nom"
            fullWidth
            margin="normal"
            value={currentItem?.nompersonnel || currentItem?.nomPartenaire || ''}
            onChange={(e) => setCurrentItem({
              ...currentItem,
              nompersonnel: e.target.value,
              nomPartenaire: e.target.value,
            })}
          />
          <TextField
            label="Prénom"
            fullWidth
            margin="normal"
            value={currentItem?.prenompersonnel || ''}
            onChange={(e) => setCurrentItem({ ...currentItem, prenompersonnel: e.target.value })}
            disabled={dialogType === 'partenaire'}
          />
          <TextField
            label="Email"
            fullWidth
            margin="normal"
            value={currentItem?.emailpersonnel || currentItem?.emailPartenaire || ''}
            onChange={(e) => setCurrentItem({
              ...currentItem,
              emailpersonnel: e.target.value,
              emailPartenaire: e.target.value,
            })}
          />
          <TextField
            label="Téléphone"
            fullWidth
            margin="normal"
            value={currentItem?.telephonepersonnel || currentItem?.telephonePartenaire || ''}
            onChange={(e) => setCurrentItem({
              ...currentItem,
              telephonepersonnel: e.target.value,
              telephonePartenaire: e.target.value,
            })}
          />
          <TextField
            label="Adresse"
            fullWidth
            margin="normal"
            value={currentItem?.adressepersonnel || currentItem?.adressePartenaire || ''}
            onChange={(e) => setCurrentItem({
                ...currentItem,
                adressepersonnel: e.target.value,
                adressePartenaire: e.target.value,
            })}
            />
          {dialogType === 'personnel' ? (
            <>
              <TextField
                label="Département"
                fullWidth
                margin="normal"
                value={currentItem?.departementpersonnel || ''}
                onChange={(e) => setCurrentItem({ ...currentItem, departementpersonnel: e.target.value })}
              />
              <TextField
                label="Rôle"
                fullWidth
                margin="normal"
                select
                value={currentItem?.rolepersonnel || ''}
                onChange={(e) => setCurrentItem({ ...currentItem, rolepersonnel: e.target.value })}
              >
                <MenuItem value="MEDECIN">Médecin</MenuItem>
                <MenuItem value="GESTIONNAIRE">Gestionnaire</MenuItem>
                <MenuItem value="MECANICIEN">Mécanicien</MenuItem>
              </TextField>
              <TextField
                label="Mot de passe"
                fullWidth
                margin="normal"
                type="password"
                value={currentItem?.motdepassepersonnel || ''}
                onChange={(e) => setCurrentItem({ ...currentItem, motdepassepersonnel: e.target.value })}
                />
            </>
          ) : (
            <TextField
              label="Type de Service"
              fullWidth
              margin="normal"
              value={currentItem?.typeService || ''}
              onChange={(e) => setCurrentItem({ ...currentItem, typeService: e.target.value })}
            />
          )}
          <Button variant="contained" onClick={handleSubmit} fullWidth sx={{ mt: 2 }}>
            Enregistrer
          </Button>
        </DialogContent>
      </Dialog>
    </Box>
  );
};

export default GestionPersonnel;
