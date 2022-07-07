package com.example.projetcaisse.model.entity;

import lombok.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Entity
@Table(name = "PRODUIT")
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Produit implements Serializable {

    /**
     * generated serial ID
     */
    private static final long serialVersionUID = 1L;

    /**
     * Produit id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "prod_generator")
    @SequenceGenerator(name = "prod_generator", sequenceName = "prod_SEQ", initialValue = 1, allocationSize = 1)
    @Column(name = "idProduit", updatable = false, nullable = false)
    private Long idProduit ;

    @Column(name="code")
    private String code ;

    @Column(name="libelle")
    private String libelle ;

    @Column(name="description")
    private String description ;

    @Column(name="prix")
    private float prix ;

    @Column(name="qte")
    private int qte ;

    @Column(name="dateAjout")
    private Date dateAjout ;

    @Column(name="dateModif")
    private Date dateModif ;

    @Column(name="active")
    private int active ;

    @Column(name="disponible")
    private String disponible ;


    @OneToMany(mappedBy="produit")
    private List<Reservation> reservations;

    public Long getIdProduit() {
        return idProduit;
    }

    public String getCode() {
        return code;
    }

    public String getLibelle() {
        return libelle;
    }

    public String getDescription() {
        return description;
    }

    public float getPrix() {
        return prix;
    }

    public int getQte() {
        return qte;
    }

    public Date getDateAjout() {
        return dateAjout;
    }

    public Date getDateModif() {
        return dateModif;
    }

    public int getActive() {
        return active;
    }

    public void setIdProduit(Long idProduit) {
        this.idProduit = idProduit;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public void setQte(int qte) {
        this.qte = qte;
    }

    public void setDateAjout(Date dateAjout) {
        this.dateAjout = dateAjout;
    }

    public void setDateModif(Date dateModif) {
        this.dateModif = dateModif;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public String getDisponible() {
        return disponible;
    }

    public void setDisponible(String disponible) {
        this.disponible = disponible;
    }
}

