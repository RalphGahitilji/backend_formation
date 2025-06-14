package com.dgti.tache.domain;

import java.time.LocalDate;


import com.dgti.tache.domain.enumaration.Etape;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tache {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="libelle")
    private String libelle;
    @Column(name="description")
    private String description;
    @Column(name="lieu")
    private String lieu;
    @Column(name="commentaire")
    private String commentaire;
    @Column(name="date_debut")
    private LocalDate dateDebut;
    @Column(name="date_fin")
    private LocalDate dateFin;

    @Enumerated(EnumType.STRING)
    @Column(name = "etape", nullable = false) // Enumere les etapes de la tache
    private Etape etape; // Enumere les etapes de la tache

    @ManyToOne
    @JsonIgnore // Permet d'ignorer la relation lors de la serialisation JSON pour eviter les boucles infinies
    private Domaine domaine; // Domaine auquel la tache est associee


    @Override
    public String toString() {
        return "Tache{" +
                "id=" + id +
                ", libelle='" + libelle + '\'' +
                ", description='" + description + '\'' +
                ", dateDebut=" + dateDebut +
                ", dateFin=" + dateFin +
                ", lieu='" + lieu + '\'' +
                ", commentaire='" + commentaire + '\'' +
                ", etape=" + etape +
                ", domaine=" + domaine +
                '}';
    }

}
