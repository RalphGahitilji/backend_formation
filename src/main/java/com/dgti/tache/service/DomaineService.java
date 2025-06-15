package com.dgti.tache.service;

import org.springframework.stereotype.Service;
import com.dgti.tache.domain.Domaine;
import com.dgti.tache.repository.DomaineRepository;
import lombok.RequiredArgsConstructor;
import java.util.List;

@Service // Permet de declarer que c'est un service Spring
@RequiredArgsConstructor // Permet de generer un constructeur avec tous les parametres pour injecter le TacheRepository
public class DomaineService {
    private final DomaineRepository domaineRepository; // Déclaration du repository pour les domaines

    public List<Domaine> getAllDomaines() {
        return domaineRepository.findAll(); // Récupérer tous les domaines de la base de données
    }

    public Domaine createDomaine(Domaine domaine) {
        return domaineRepository.save(domaine); //Enregistrement d'un nouveau domaine dans la base de données
    }

    public Domaine getDomaineById(Long id) {
        return domaineRepository.findById(id).orElseThrow(()-> new RuntimeException("Le domaine n'existe pas avec cet ID:" + id)); // Récupérer un domaine par son ID, renvoie un Optional
    }

    public Domaine updateDomaine(Long id, Domaine domaine) {
        if(!domaineRepository.existsById(id)){
            throw new RuntimeException("Le domaine n'existe pas avec cet ID:" + id); // Vérifier si le domaine existe avant de le mettre à jour            
        }
        domaine.setId(id); // Mettre à jour l'ID du domaine
        return domaineRepository.save(domaine); // Enregistrer le domaine mis à jour dans la base de données
    }

    public void deleteDomaine(Long id){
        if(domaineRepository.existsById(id)){
            domaineRepository.deleteById(id);
        } else {
            throw new RuntimeException("Le domaine n'existe pas avec cet ID:" + id); // Vérifier si le domaine existe avant de le supprimer
        }
    }

}
