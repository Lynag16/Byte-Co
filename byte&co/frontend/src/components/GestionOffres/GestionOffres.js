import React, { useState, useEffect } from "react";
import "./GestionOffres.css";

const GestionOffres = () => {
    const [offres, setOffres] = useState([]);
    const [showModal, setShowModal] = useState(false);
    const [currentOffre, setCurrentOffre] = useState({});
    const [isEditing, setIsEditing] = useState(false);

    useEffect(() => {
        fetch("http://localhost:4000/offres")
            .then((response) => response.json())
            .then((data) => setOffres(data))
            .catch((error) => console.error("Erreur:", error));
    }, []);

    const handleDelete = (id) => {
        fetch(`http://localhost:4000/offres/${id}`, { method: "DELETE" })
            .then(() => setOffres(offres.filter((offre) => offre.id !== id)))
            .catch((error) => console.error("Erreur:", error));
    };

    const handleAddOrEdit = (e) => {
        e.preventDefault();
        if (isEditing) {
            fetch(`http://localhost:4000/offres/${currentOffre.id}`, {
                method: "PUT",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify(currentOffre),
            })
                .then(() => {
                    setOffres(
                        offres.map((offre) =>
                            offre.id === currentOffre.id ? currentOffre : offre
                        )
                    );
                    setShowModal(false);
                })
                .catch((error) => console.error("Erreur:", error));
        } else {
            fetch("http://localhost:4000/offres", {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify(currentOffre),
            })
                .then((response) => response.json())
                .then((newOffre) => {
                    setOffres([...offres, newOffre]);
                    setShowModal(false);
                })
                .catch((error) => console.error("Erreur:", error));
        }
    };

    const handleOpenModal = (offre = {}) => {
        setCurrentOffre(offre);
        setIsEditing(!!offre.id);
        setShowModal(true);
    };

    return (
        <div className="gestion-offres-container">
            <h1>Gestion des Offres</h1>
            <button className="add-offer-btn" onClick={() => handleOpenModal()}>
                Ajouter une offre
            </button>
            <table className="offers-table">
                <thead>
                    <tr>
                        <th>ID Offre</th>
                        <th>Nom de l'offre</th>
                        <th>Prix (€)</th>
                        <th>Conditions d'éligibilité</th>
                        <th>Description</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    {offres.map((offre) => (
                        <tr key={offre.id}>
                            <td>{offre.id}</td>
                            <td>{offre.NomOffre}</td>
                            <td>{offre.Prix}</td>
                            <td>{offre.ConditionsEligibilite}</td>
                            <td>{offre.DescriptionOffre}</td>
                            <td className="actions">
                                <button
                                    className="edit-btn"
                                    onClick={() => handleOpenModal(offre)}
                                >
                                    ✏️
                                </button>
                                <button
                                    className="delete-btn"
                                    onClick={() => handleDelete(offre.id)}
                                >
                                    🗑️
                                </button>
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>

            {showModal && (
                <div className="modal">
                    <div className="modal-content">
                        <h2>{isEditing ? "Modifier une offre" : "Ajouter une offre"}</h2>
                        <form onSubmit={handleAddOrEdit}>
                            <input
                                type="text"
                                placeholder="Nom de l'offre"
                                value={currentOffre.NomOffre || ""}
                                onChange={(e) =>
                                    setCurrentOffre({
                                        ...currentOffre,
                                        NomOffre: e.target.value,
                                    })
                                }
                            />
                            <input
                                type="number"
                                placeholder="Prix (€)"
                                value={currentOffre.Prix || ""}
                                onChange={(e) =>
                                    setCurrentOffre({
                                        ...currentOffre,
                                        Prix: e.target.value,
                                    })
                                }
                            />
                            <input
                                type="text"
                                placeholder="Conditions d'éligibilité"
                                value={currentOffre.ConditionsEligibilite || ""}
                                onChange={(e) =>
                                    setCurrentOffre({
                                        ...currentOffre,
                                        ConditionsEligibilite: e.target.value,
                                    })
                                }
                            />
                            <textarea
                                placeholder="Description de l'offre"
                                value={currentOffre.DescriptionOffre || ""}
                                onChange={(e) =>
                                    setCurrentOffre({
                                        ...currentOffre,
                                        DescriptionOffre: e.target.value,
                                    })
                                }
                            />
                            <button type="submit" className="save-btn">
                                {isEditing ? "Modifier" : "Ajouter"}
                            </button>
                            <button
                                type="button"
                                className="cancel-btn"
                                onClick={() => setShowModal(false)}
                            >
                                Annuler
                            </button>
                        </form>
                    </div>
                </div>
            )}
        </div>
    );
};

export default GestionOffres;
