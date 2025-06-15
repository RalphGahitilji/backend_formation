package com.dgti.tache.controller;

import java.util.List;
import java.util.Optional;

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
import com.dgti.tache.domain.Tache;
import com.dgti.tache.repository.DomaineRepository;
import com.dgti.tache.repository.TacheRepository;
import com.dgti.tache.service.TacheServiceImpl;

import lombok.RequiredArgsConstructor;

@CrossOrigin(origins="*") // Permettre les requêtes cross-origin depuis l'URL du frontend
@RequestMapping("/api/taches") // Point d'entrée des requetes HTTP pour les Taches
@RestController // Controller qui renvoies des donnees au format JSON
@RequiredArgsConstructor // Génère un constructeur avec les arguments requis pour l'injection de dépendances
public class TacheController {
    private final TacheServiceImpl TacheServiceImpl;
    private final TacheRepository tacheRepository;
    private final DomaineRepository domaineRepository;

    @PostMapping // Endpoint pour créer un nouveau Tache
    public Tache create(@RequestBody Tache Tache) {
        return TacheServiceImpl.creerTache(Tache); // Créer un nouveau Tache
    }

    @PostMapping
    public ResponseEntity<Tache> creerTache(@RequestBody Tache tache) {
        if (tache.getDomaine() != null && tache.getDomaine().getId() != null) {
            Domaine domaine = domaineRepository.findById(tache.getDomaine().getId())
                .orElseThrow(() -> new RuntimeException("Domaine non trouvé"));
            tache.setDomaine(domaine);
        }
        Tache newTache = tacheRepository.save(tache);
        return new ResponseEntity<>(newTache, HttpStatus.CREATED);
    }

    @GetMapping("/{id}") // Endpoint pour récupérer un Tache par son ID
    public ResponseEntity<Tache> getTacheById(@PathVariable Long id) {
         Optional<Tache> optionalTache = TacheServiceImpl.recupererTacheParId(id); // Récupérer un Tache par son ID
        if (optionalTache == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Si le Tache n'est pas trouvé, retourner un statut NOT FOUND avce le statut 400
        }
        return ResponseEntity.ok(optionalTache.get()); // Retourner le Tache trouvé avec le statut OK 200
    }

    @GetMapping("/e/{id}") // Endpoint pour récupérer tous les Taches
    public ResponseEntity<Tache> getTacheByIde(@PathVariable Long id){
        return tacheRepository.findById(id)
                .map(tache -> ResponseEntity.ok(tache))  // Si le Tache est trouvé, retourner le Tache avec un statut OK
                .orElse(ResponseEntity.notFound().build()); // Si le Tache n'est pas trouvé, retourner un statut NOT FOUND
    }

    @GetMapping
    public List<Tache> getAllTaches() {
        return TacheServiceImpl.recupTache(); // Récupérer tous les Taches
    }

    @GetMapping
    public ResponseEntity<List<Tache>> getAllTachesList() {
        List<Tache> taches = tacheRepository.findAll(); // Récupérer tous les Taches de la base de données
        return ResponseEntity.ok(taches); // Retourner la liste des Taches avec un statut OK
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTache(@PathVariable Long id) {
        Optional<Tache> optionalTache = TacheServiceImpl.recupererTacheParId(id); // Récupérer le Tache par son ID
        if (optionalTache == null) {
            return  ResponseEntity.notFound().build(); // Si le Tache n'est pas trouvé, retourner un statut NOT FOUND
        } else {
            TacheServiceImpl.deleteTache(id); // Supprimer un Tache par son ID
            return ResponseEntity.noContent().build(); // Retourner un statut NO CONTENT après la suppression
        }
    }

    @DeleteMapping("/supprimer/{id}")
    public ResponseEntity<Void> deleteTacheById(@PathVariable Long id) {
        if (!tacheRepository.existsById(id)) {
            return ResponseEntity.notFound().build(); // Si le Tache n'existe pas, retourner un statut NOT FOUND
        }
        tacheRepository.deleteById(id); // Supprimer le Tache par son ID
        return ResponseEntity.noContent().build(); // Retourner un statut NO CONTENT après la suppression   
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tache> updateTache(@PathVariable Long id, @RequestBody Tache tache) {
        Optional<Tache> optionalTache = TacheServiceImpl.recupererTacheParId(id); // Récupérer le Tache par son ID
        if (optionalTache == null) {
            return ResponseEntity.notFound().build(); // Si le Tache n'est pas trouvé, retourner un statut NOT FOUND
        }
        tache.setId(id);
        Tache updatedTache = TacheServiceImpl.UpdateTache(id, tache); // Mettre à jour le Tache
        return ResponseEntity.ok(updatedTache); // Retourner le Tache mis à jour avec un statut OK
    }
    
    @PutMapping("/t/{id}")
    public ResponseEntity<Tache> modifierTache(@PathVariable Long id, @RequestBody Tache tache) {
        if (!tacheRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        tache.setId(id);
        Tache tacheModifiee = tacheRepository.save(tache);
        return ResponseEntity.ok(tacheModifiee);
    }

}
