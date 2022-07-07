package com.example.projetcaisse.rest.dto;

import com.example.projetcaisse.model.entity.Reservation;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FactureDto {
    private Long idFacture ;
    private String code ;
    private Float prixSansTVA;
    private Float tva;
    private Float prixCompriseTVA;
    private Float prixUnitaire;
    private Date dateAjout ;
    private String username;
    private Long telephone;
    private String adresse;
    private String libelle ;

}
