import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { DataGrid } from '@mui/x-data-grid';
import { Box, Button, Dialog, DialogTitle, DialogContent, IconButton, TextField, MenuItem } from '@mui/material';
import { Close } from '@mui/icons-material';

const GestionPersonnel = () => {
  const [personnel, setPersonnel] = useState([]);
  const [partners, setPartners] = useState([]);
  const [openDialog, setOpenDialog] = useState(false);
  const [dialogType, setDialogType] = useState('');
  const [currentItem, setCurrentItem] = useState(null);

  useEffect(() => {
    // Fonction pour récupérer les données des personnels
    const fetchPersonnelData = async () => {
      try {
        const response = await axios.get('http://localhost:8081/api/personnels');
        console.log('Réponse des personnels :', response.data);
        if (Array.isArray(response.data)) {
          const personnelWithId = response.data.map(item => ({ ...item, id: item.idpersonnel }));
          setPersonnel(personnelWithId);
        } else {
          console.error('Les données des personnels ne sont pas sous forme de tableau');
        }
      } catch (error) {
        console.error('Erreur lors du chargement des personnels:', error);
      }
    };

    // Fonction pour récupérer les données des partenaires
    const fetchPartnersData = async () => {
      try {
        const response = await axios.get('http://localhost:8081/api/partenaires');
        console.log('Réponse des partenaires :', response.data);
        if (Array.isArray(response.data)) {
          const partnersWithId = response.data.map(item => ({ ...item, id: item.idPartenaire }));
          setPartners(partnersWithId);
        } else {
          console.error('Les données des partenaires ne sont pas sous forme de tableau');
        }
      } catch (error) {
        console.error('Erreur lors du chargement des partenaires:', error);
      }
    };

    fetchPersonnelData();
    fetchPartnersData();
  }, []);

  const handleOpenDialog = (type, item) => {
    setDialogType(type);
    setCurrentItem(item);
    setOpenDialog(true);
  };

  const handleCloseDialog = () => {
    setOpenDialog(false);
    setCurrentItem(null);
  };

  const handleSubmit = () => {
    if (dialogType === 'personnel') {
      if (currentItem?.id) {
        axios.put(`http://localhost:8081/api/personnels/${currentItem.id}`, currentItem)
        .then(response => {
          setPersonnel(prev => prev.map(p => p.id === currentItem.id ? response.data : p));
          handleCloseDialog();
        })
        .catch(error => console.error('Erreur lors de la mise à jour du personnel:', error));
      } else {
        axios.post('http://localhost:8081/api/personnels', currentItem)
        .then(response => {
          setPersonnel(prev => [...prev, response.data]);
          handleCloseDialog();
        })
        .catch(error => console.error('Erreur lors de la création du personnel:', error));
      }
    } else if (dialogType === 'partenaire') {
      if (currentItem?.idPartenaire) {
        axios.put(`http://localhost:8081/api/partenaires/${currentItem.idPartenaire}`, currentItem)
        .then(response => {
          setPartners(prev => prev.map(p => p.idPartenaire === currentItem.idPartenaire ? response.data : p));
          handleCloseDialog();
        })
        .catch(error => console.error('Erreur lors de la mise à jour du partenaire:', error));
      } else {
        axios.post('http://localhost:8081/api/partenaires', currentItem)
        .then(response => {
          setPartners(prev => [...prev, response.data]);
          handleCloseDialog();
        })
        .catch(error => console.error('Erreur lors de la création du partenaire:', error));
      }
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
      field: 'actions', headerName: 'Actions', flex: 1,
      renderCell: (params) => (
        <Button variant="outlined" size="small" onClick={() => handleOpenDialog('personnel', params.row)}>
          Modifier
        </Button>
      ),
    },
  ];

  const columnsPartners = [
    { field: 'nomPartenaire', headerName: 'Nom', flex: 1 },
    { field: 'zonegeo', headerName: 'Zone Géographique', flex: 1 },
    { field: 'emailPartenaire', headerName: 'Email', flex: 1 },
    { field: 'telephonePartenaire', headerName: 'Téléphone', flex: 1 },
    {
      field: 'actions', headerName: 'Actions', flex: 1,
      renderCell: (params) => (
        <Button variant="outlined" size="small" onClick={() => handleOpenDialog('partenaire', params.row)}>
          Modifier
        </Button>
      ),
    },
  ];

  return (
    <Box>
      <Button
        variant="contained"
        color="primary"
        onClick={() => handleOpenDialog('personnel', {})}
        style={{ marginBottom: '1rem' }}
      >
        Ajouter un personnel
      </Button>
      <Button
        variant="contained"
        color="secondary"
        onClick={() => handleOpenDialog('partenaire', {})}
        style={{ marginBottom: '1rem', marginLeft: '1rem' }}
      >
        Ajouter un partenaire
      </Button>

      <Box style={{ height: 400, width: '100%' }}>
        <h3>Gestion des personnels</h3>
        <DataGrid
          rows={personnel}
          columns={columnsPersonnel}
          pageSize={5}
          disableSelectionOnClick
          getRowId={(row) => row.id}
        />
      </Box>

      <Box style={{ height: 400, width: '100%', marginTop: '2rem' }}>
        <h3>Gestion des partenaires</h3>
        <DataGrid
          rows={partners}
          columns={columnsPartners}
          pageSize={5}
          disableSelectionOnClick
          getRowId={(row) => row.id}
        />
      </Box>

      {/* Dialog pour ajouter ou modifier un personnel/partenaire */}
      <Dialog open={openDialog} onClose={handleCloseDialog} maxWidth="sm" fullWidth>
        <DialogTitle>
          {dialogType === 'personnel' ? (currentItem?.id ? 'Modifier un personnel' : 'Ajouter un personnel') :
            (currentItem?.idPartenaire ? 'Modifier un partenaire' : 'Ajouter un partenaire')}
          <IconButton
            edge="end"
            color="inherit"
            onClick={handleCloseDialog}
            aria-label="close"
            sx={{ position: 'absolute', right: 8, top: 8 }}
          >
            <Close />
          </IconButton>
        </DialogTitle>
        <DialogContent>
          <TextField
            label="Nom"
            variant="outlined"
            fullWidth
            margin="normal"
            value={currentItem?.nompersonnel || currentItem?.nomPartenaire || ''}
            onChange={(e) => setCurrentItem({ ...currentItem, nompersonnel: e.target.value, nomPartenaire: e.target.value })}
          />
          <TextField
            label="Prénom"
            variant="outlined"
            fullWidth
            margin="normal"
            value={currentItem?.prenompersonnel || currentItem?.prenomPartenaire || ''}
            onChange={(e) => setCurrentItem({ ...currentItem, prenompersonnel: e.target.value, prenomPartenaire: e.target.value })}
          />
          <TextField
            label="Email"
            variant="outlined"
            fullWidth
            margin="normal"
            value={currentItem?.emailpersonnel || currentItem?.emailPartenaire || ''}
            onChange={(e) => setCurrentItem({ ...currentItem, emailpersonnel: e.target.value, emailPartenaire: e.target.value })}
          />
          <TextField
            label="Téléphone"
            variant="outlined"
            fullWidth
            margin="normal"
            value={currentItem?.telephonepersonnel || currentItem?.telephonePartenaire || ''}
            onChange={(e) => setCurrentItem({ ...currentItem, telephonepersonnel: e.target.value, telephonePartenaire: e.target.value })}
          />
          <TextField
            label="Adresse"
            variant="outlined"
            fullWidth
            margin="normal"
            value={currentItem?.adressepersonnel || currentItem?.adressePartenaire || ''}
            onChange={(e) => setCurrentItem({ ...currentItem, adressepersonnel: e.target.value, adressePartenaire: e.target.value })}
          />
          {dialogType === 'personnel' ? (
            <TextField
              label="Rôle"
              variant="outlined"
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
          ) : (
            <TextField
              label="Type de Service"
              variant="outlined"
              fullWidth
              margin="normal"
              value={currentItem?.typeService || ''}
              onChange={(e) => setCurrentItem({ ...currentItem, typeService: e.target.value })}
            />
          )}

          <Button
            onClick={handleSubmit}
            variant="contained"
            color="primary"
            fullWidth
            style={{ marginTop: '1rem' }}
          >
            {dialogType === 'personnel' ? 'Enregistrer Personnel' : 'Enregistrer Partenaire'}
          </Button>
        </DialogContent>
      </Dialog>
    </Box>
  );
};

export default GestionPersonnel;
