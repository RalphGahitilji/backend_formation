package com.dgti.tache.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.dgti.tache.domain.Domaine;
import com.dgti.tache.service.DomaineService;
import com.dgti.tache.repository.DomaineRepository;
import lombok.RequiredArgsConstructor;


@CrossOrigin(origins="*") // Permettre les requêtes cross-origin depuis l'URL du frontend
@RequestMapping("/api/domaines") // Point d'entrée des requetes HTTP pour les domaines
@RestController // Controller qui renvoies des donnees au format JSON
@RequiredArgsConstructor // Génère un constructeur avec les arguments requis pour l'injection de dépendances
public class DomaineController {
    private final DomaineService domaineService;
    private final DomaineRepository domaineRepository;

    @PostMapping // Endpoint pour créer un nouveau domaine
    public Domaine create(@RequestBody Domaine domaine) {
        return domaineService.createDomaine(domaine); // Créer un nouveau domaine
    }

    @PostMapping // Endpoint pour créer un domaine (alternative)
    public ResponseEntity<Domaine> createD(@RequestBody Domaine domaine) {
        Domaine newDomaine = domaineRepository.save(domaine); // Enregistrer le domaine dans la base de données
        return new ResponseEntity<>(newDomaine, HttpStatus.CREATED); // Retourner le domaine créé
    }

    @GetMapping("/{id}") // Endpoint pour récupérer un domaine par son ID
    public ResponseEntity<Domaine> getDomaineById(@PathVariable Long id) {
        Domaine domaine = domaineService.getDomaineById(id); // Récupérer un domaine par son ID
        if (domaine == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Si le domaine n'est pas trouvé, retourner un statut NOT FOUND
        }
        return new ResponseEntity<>(domaine, HttpStatus.OK); // Retourner le domaine trouvé
    }

    @GetMapping("/e/{id}") // Endpoint pour récupérer tous les domaines
    public ResponseEntity<Domaine> getDomaineByIde(@PathVariable Long id){
        return domaineRepository.findById(id)
                .map(domaine -> new ResponseEntity<>(domaine, HttpStatus.OK)) // Si le domaine est trouvé, retourner le domaine avec un statut OK
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND)); // Si le domaine n'est pas trouvé, retourner un statut NOT FOUND
    }

    @GetMapping
    public List<Domaine> getAllDomaines() {
        return domaineService.getAllDomaines(); // Récupérer tous les domaines
    }

    @GetMapping
    public ResponseEntity<List<Domaine>> getAllDomainesList() {
        List<Domaine> domaines = domaineRepository.findAll(); // Récupérer tous les domaines de la base de données
        return new ResponseEntity<>(domaines, HttpStatus.OK); // Retourner la liste des domaines avec un statut OK
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDomaine(@PathVariable Long id) {
        Domaine domaine = domaineService.getDomaineById(id); // Récupérer le domaine par son ID
        if (domaine == null) {
            return  ResponseEntity.notFound().build(); // Si le domaine n'est pas trouvé, retourner un statut NOT FOUND
        } else {
            domaineService.deleteDomaine(id); // Supprimer un domaine par son ID
            return ResponseEntity.noContent().build(); // Retourner un statut NO CONTENT après la suppression
        }
    }

    @DeleteMapping("/supprimer/{id}")
    public ResponseEntity<Void> deleteDomaineById(@PathVariable Long id) {
        if (!domaineRepository.existsById(id)) {
            return ResponseEntity.notFound().build(); // Si le domaine n'existe pas, retourner un statut NOT FOUND
        }
        domaineRepository.deleteById(id); // Supprimer le domaine par son ID
        return ResponseEntity.noContent().build(); // Retourner un statut NO CONTENT après la suppression   
    }

    @PutMapping("/{id}") // Endpoint pour mettre à jour un domaine par son ID
    public ResponseEntity<Domaine> updateDomaine(@PathVariable Long id, @RequestBody Domaine domaine) {
        if (!domaineRepository.existsById(id)) {
            return ResponseEntity.notFound().build(); // Si le domaine n'existe pas, retourner un statut NOT FOUND
        }
        domaine.setId(id); // Mettre à jour l'ID du domaine
        Domaine updatedDomaine = domaineRepository.save(domaine); // Enregistrer le domaine mis à jour
        return new ResponseEntity<>(updatedDomaine, HttpStatus.OK); // Retourner le domaine mis à jour avec un statut OK
    }

    @PutMapping("/d/{id}") // Endpoint pour modifier un domaine par son ID
    public ResponseEntity<Domaine> modifierDomaine(@PathVariable Long id, @RequestBody Domaine domaine) {
        if (!domaineRepository.existsById(id)) {
            return ResponseEntity.notFound().build(); // Si le domaine n'existe pas, retourner un statut NOT FOUND
        }
        domaine.setId(id); // Mettre à jour l'ID du domaine
        Domaine domaineModifie = domaineRepository.save(domaine); // Enregistrer le domaine modifié
        return new ResponseEntity<>(domaineModifie, HttpStatus.OK); // Retourner le domaine modifié avec un statut OK
    }
}
 