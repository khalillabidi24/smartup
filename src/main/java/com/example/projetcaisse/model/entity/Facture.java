package com.example.projetcaisse.model.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "FACTURE")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Facture implements Serializable {

    /**
     * generated serial ID
     */
    private static final long serialVersionUID = 1L;

    /**
     * Produit id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "fact_generator")
    @SequenceGenerator(name = "fact_generator", sequenceName = "fact_SEQ", initialValue = 1, allocationSize = 1)
    @Column(name = "idFacture", updatable = false, nullable = false)
    private Long idFacture ;

    @Column(name="code")
    private String code ;

    @Column(name="PRIX_SANS_TVA" )
    private Float prixSansTVA;

    @Column(name = "TVA")
    private Float tva;

    @Column(name = "PRIX_AVEC_TVA")
    private Float prixCompriseTVA;


    @Column(name="dateAjout")
    private Date dateAjout ;

    @Column(name="active")
    private int active=0 ;
    private String username;
    private Long telephone;
    private String adresse;
    private Long idUser ;


    @OneToMany(mappedBy="facture")
    private List<Reservation> reservations;

    public Long getIdFacture() {
        return idFacture;
    }

    public void setIdFacture(Long idFacture) {
        this.idFacture = idFacture;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Float getPrixSansTVA() {
        return prixSansTVA;
    }

    public void setPrixSansTVA(Float prixSansTVA) {
        this.prixSansTVA = prixSansTVA;
    }

    public Float getTva() {
        return tva;
    }

    public void setTva(Float tva) {
        this.tva = tva;
    }

    public Float getPrixCompriseTVA() {
        return prixCompriseTVA;
    }

    public void setPrixCompriseTVA(Float prixCompriseTVA) {
        this.prixCompriseTVA = prixCompriseTVA;
    }


    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getTelephone() {
        return telephone;
    }

    public void setTelephone(Long telephone) {
        this.telephone = telephone;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
}
