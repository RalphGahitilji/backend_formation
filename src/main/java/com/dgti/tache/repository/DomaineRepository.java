package com.dgti.tache.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dgti.tache.domain.Domaine;

public interface DomaineRepository extends JpaRepository<Domaine, Long>{
}
