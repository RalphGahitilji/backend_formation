package com.dgti.tache.domain;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity // Permet de declarer que c'est une classe persistante (table BD)
@NoArgsConstructor // Permet de generer un constructeur sans parametre
@AllArgsConstructor // permet de generer un constructeur avec tous les parametres
@Data // Permet de generer les accesseurs et mutateurs (getters et setters)
@Table (name = "domaine") // Permet de specifier le nom de la table dans la base de donnees
public class Domaine {
    @Id // Indique que c'est la cle primaire de la table
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Indique que la valeur de la cle primaire est genere automatiquement (auto-increment)
    private Long id;
    @Column(name = "libelle") // Indique que c'est une colonne de la table, non nullable et avec une longueur maximale de 50 caracteres
    private String libelle;
    @Column(name = "is_dev", nullable = false) // Indique que c'est une colonne de la table, non nullable
    private boolean isDev = true;


    @OneToMany(mappedBy = "domaine") // Permet de specifier la relation un a plusieurs avec la classe Tache
    private List<Tache> taches; // Permet de specifier la relation un a plusieurs avec la classe Tache


    @Override
    public String toString() {
        return "Domaine{" +
                "id=" + id +
                ", libelle='" + libelle + '\'' +
                '}';
    }
    // public Domaine() {
    // }
    
    // public Domaine(Long id, String libelle) {
    //     this.id = id;
    //     this.libelle = libelle;
    // }

    // public Long getId() {
    //     return id;
    // }

    // public void setId(Long id) {
    //     this.id = id;
    // }

    // public String getLibelle() {
    //     return libelle; 
    // }

    // public void setLibelle(String libelle) {
    //     this.libelle = libelle;
    // }
}
