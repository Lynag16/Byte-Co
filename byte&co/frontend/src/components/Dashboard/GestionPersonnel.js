import React, { useState, useEffect } from 'react';
import './GestionsPersonnel.css';
import { motion } from 'framer-motion';

const GestionsPersonnel = () => {
  const [personnelList, setPersonnelList] = useState([]);
  const [newPersonnel, setNewPersonnel] = useState({ nom: '', email: '', poste: '', dateCreation: '' });
  const [showForm, setShowForm] = useState(false);

  useEffect(() => {
    // Fetch personnel data from the backend
    const fetchPersonnel = async () => {
      const response = await fetch('http://localhost:8082/api/auth/personnel');
      const data = await response.json();
      setPersonnelList(data);
    };

    fetchPersonnel();
  }, []);

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setNewPersonnel((prev) => ({ ...prev, [name]: value }));
  };

  const handleAddPersonnel = async () => {
    const response = await fetch('http://localhost:8082/api/auth/register-personnel', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(newPersonnel),
    });

    if (response.ok) {
      const addedPersonnel = await response.json();
      setPersonnelList([...personnelList, addedPersonnel]);
      setShowForm(false);
    }
  };

  return (
    <div className="personnel-management">
      <motion.h2
                        initial={{ opacity: 0, y: -20 }}
                        animate={{ opacity: 1, y: 0 }}
                        transition={{ duration: 0.5 }}
                        className="declaration-sinistre-title"
                      >
                        Gestion du Personnel
                      </motion.h2>
      <button onClick={() => setShowForm(!showForm)}>Ajouter un personnel</button>

      {showForm && (
        <div className="form-container">
          <input type="text" name="nom" placeholder="Nom" onChange={handleInputChange} />
          <input type="email" name="email" placeholder="Email" onChange={handleInputChange} />
          <input type="text" name="poste" placeholder="Poste" onChange={handleInputChange} />
          <input type="date" name="dateCreation" placeholder="Date de création" onChange={handleInputChange} />
          <button onClick={handleAddPersonnel}>Ajouter</button>
        </div>
      )}

      <table className="personnel-table">
        <thead>
          <tr>
            <th>Nom</th>
            <th>Email</th>
            <th>Poste</th>
            <th>Date de création</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          {personnelList.map((personnel) => (
            <tr key={personnel.id}>
              <td>{personnel.nom}</td>
              <td>{personnel.email}</td>
              <td>{personnel.poste}</td>
              <td>{personnel.dateCreation}</td>
              <td>
                <button>Modifier</button>
                <button>Supprimer</button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default GestionsPersonnel;
