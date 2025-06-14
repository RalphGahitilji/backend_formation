package com.dgti.tache.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.dgti.tache.domain.Tache;

public interface TacheRepository extends JpaRepository<Tache, Long> {
    // Define any custom query methods if needed
    //methode pour trouver le domaine par tache
    // List<Tache> findByDomaine(Domaine domaine);//Trouver des tâches par domaine
}
