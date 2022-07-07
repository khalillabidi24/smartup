package com.example.projetcaisse.rest.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProduitDto {
    private Long idProduit ;
    private String code ;
    private String libelle ;
    private String description ;
    private float prix ;
    private int qte ;
    private Date dateAjout ;
    private Date dateModif ;
    private int active ;

}
