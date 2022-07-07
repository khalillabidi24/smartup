package com.example.projetcaisse.model.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "RECLAMATION")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Reclamation implements Serializable {
    /**
     * generated serial ID
     */
    private static final long serialVersionUID = 1L;

    /**
     * User id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rec_generator")
    @SequenceGenerator(name = "rec_generator", sequenceName = "rec_SEQ", initialValue = 1, allocationSize = 1)
    @Column(name = "idRec", updatable = false, nullable = false)
    private Long idRec ;

    @Column(name="titre")
    private String titre ;

    @Column(name="confirmation")
    private String confirmation ;

    @Column(name="description" ,updatable = false)
    private String description ;

    @Column(name="dateAjout",updatable = false)
    private Date dateAjout ;

    @Column(name="dateReponse")
    private Date dateReponse ;

    @Column(name="idclient",updatable = false)
    private Long idclient ;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user")
    private Utilisateur utilisateur;

    public Long getIdRec() {
        return idRec;
    }

    public void setIdRec(Long idRec) {
        this.idRec = idRec;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateAjout() {
        return dateAjout;
    }

    public void setDateAjout(Date dateAjout) {
        this.dateAjout = dateAjout;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }


}
