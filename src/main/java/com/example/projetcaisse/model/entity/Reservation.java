package com.example.projetcaisse.model.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "RESERVATION")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Reservation implements Serializable {
    /**
     * generated serial ID
     */
    private static final long serialVersionUID = 1L;

    /**
     * Produit id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Reservat_generator")
    @SequenceGenerator(name = "Reservat_generator", sequenceName = "Reservat_SEQ", initialValue = 1, allocationSize = 1)
    @Column(name = "idReservation", updatable = false, nullable = false)
    private Long idReservation ;

    @Column(name="qte")
    private int qte ;

    @Column(name="qtefinal",updatable = false)
    private int qtefinal ;

    @Column(name="libelle")
    private String libelle ;

    @Column(name="prix")
    private float prix ;

    @Column(name="dateAjout")
    private Date dateAjout ;

    private Long idClient ;
    private int active=0 ;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user")
    private Utilisateur utilisateur;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_Prod")
    private Produit produit;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_facture")
    private Facture facture;

    public Long getIdReservation() {
        return idReservation;
    }

    public void setIdReservation(Long idReservation) {
        this.idReservation = idReservation;
    }

    public int getQte() {
        return qte;
    }

    public void setQte(int qte) {
        this.qte = qte;
    }

    public int getQtefinal() {
        return qtefinal;
    }

    public void setQtefinal(int qtefinal) {
        this.qtefinal = qtefinal;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public Date getDateAjout() {
        return dateAjout;
    }

    public void setDateAjout(Date dateAjout) {
        this.dateAjout = dateAjout;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }
}
