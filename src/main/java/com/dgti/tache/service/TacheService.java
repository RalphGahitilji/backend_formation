package com.dgti.tache.service;

import java.util.List;
import java.util.Optional;

import com.dgti.tache.domain.Tache;

public interface TacheService // Interface pour le service de gestion des tâches
{
    // Tache creerTache(Tache tache); // Créer une nouvelle tâche
    // List<Tache> recupererToutesLesTaches(); // Récupérer toutes les tâches
    Optional<Tache> recupererTacheParId(Long id); // Récupérer une tâche par son ID
    // Tache mettreAJourTache(Long id, Tache tache); // Mettre à jour une tâche existante
    // void supprimerTache(Long id); // Supprimer une tâche par son ID
}
