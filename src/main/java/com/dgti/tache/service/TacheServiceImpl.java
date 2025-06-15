package com.dgti.tache.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.dgti.tache.domain.Tache;
import com.dgti.tache.repository.TacheRepository;

import lombok.RequiredArgsConstructor;

@Service // Permet de declarer que c'est un service Spring
@RequiredArgsConstructor// Permet de generer un constructeur avec tous les parametres pour injecter le TacheRepository
public class TacheServiceImpl implements TacheService{
    private final TacheRepository tacheRepository;// Déclaration du repository pour les tâches
    // private final TacheService tacheService;// Déclaration du repository pour les tâches

    // Constructeur pour injecter le TacheRepository
    // public TacheServiceImpl(TacheRepository tacheRepository) {
    //     this.tacheRepository = tacheRepository;
    // }

    public Tache creerTache(Tache tache) {
        return tacheRepository.save(tache); // Enregistrer une nouvelle tâche dans la base de données
    }

    public List<Tache> recupTache() {
        return tacheRepository.findAll(); // Récupérer toutes les tâches de la base de données
    }


    // public Optional<Tache> recupTacheParId(Long id) {
    //     return  tacheService.recupererTacheParId(id);//  Récupérer une tâche par son ID, renvoie un Optional
    // }

    @Override
    public Optional<Tache> recupererTacheParId(Long id) {
        return tacheRepository.findById(id); // Récupérer une tâche par son ID, renvoie un Optional
    }

    public Tache UpdateTache(Long id,Tache tache){
        if(!tacheRepository.existsById(id)) {
            throw new RuntimeException("Tache not trouvée avec cet Id:" + id);
        } tache.setId(id);
        return tacheRepository.save(tache); // Mettre à jour une tâche existante dans la base de données
    }

    public void deleteTache(Long id){
        if(!tacheRepository.existsById(id)){
            throw new RuntimeException("Tache not trouvée avec cet Id:" + id);
        }
        tacheRepository.deleteById(id);
    }


}
