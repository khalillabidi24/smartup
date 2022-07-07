package com.example.projetcaisse.rest.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import java.util.Date;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReclamationDto {
    private Long idRec ;
    private String titre ;
    private String description ;
    private Date dateAjout ;
    private String confirmation ;
    private Date dateReponse ;
}
