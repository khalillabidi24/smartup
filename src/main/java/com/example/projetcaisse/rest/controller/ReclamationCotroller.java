package com.example.projetcaisse.rest.controller;

import com.example.projetcaisse.model.entity.Reclamation;
import com.example.projetcaisse.model.entity.Utilisateur;
import com.example.projetcaisse.rest.dto.ReclamationDto;
import com.example.projetcaisse.service.ReclamationService;
import com.example.projetcaisse.service.UtilisateurService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin
public class ReclamationCotroller {

    @Autowired
    private UtilisateurService utilisateurService ;
    @Autowired
    private ReclamationService reclamationService ;
    @Autowired
    private ModelMapper modelMapper ;
    @GetMapping("/utilisateur/{idUser}/reclamations")
    public Object Reclamationlist(@PathVariable Long idUser ) {
        List<Reclamation> reclamations= reclamationService.getReclamationList(idUser);
        Type listType = new TypeToken<List<ReclamationDto>>() {}.getType() ;
        List <ReclamationDto> reclamationDtos= modelMapper.map(reclamations,listType);
        return ResponseEntity.status(HttpStatus.OK).body(reclamationDtos);
    }

    @GetMapping("/reclamations")
    public Object ReclamationAll() {
        List<Reclamation> reclamations= reclamationService.getReclamationListAll();
        Type listType = new TypeToken<List<ReclamationDto>>() {}.getType() ;
        List <ReclamationDto> reclamationDtos= modelMapper.map(reclamations,listType);
        return ResponseEntity.status(HttpStatus.OK).body(reclamationDtos);
    }

    @GetMapping("/reclamations/{idRec}")
    public Object Reclamation(@PathVariable Long idRec ) {
        Reclamation reclamation = reclamationService.getReclamation(idRec) ;
        ReclamationDto reclamationDto= modelMapper.map(reclamation, ReclamationDto.class);
        return ResponseEntity.status(HttpStatus.OK).body(reclamationDto);
    }

    @PostMapping("/utilisateur/{idUser}/reclamations")
    public Object AddReclamation(@PathVariable Long idUser,@Validated @RequestBody ReclamationDto reclamationDto)
    {
        Reclamation reclamation = modelMapper.map(reclamationDto, Reclamation.class);
        Utilisateur utilisateur =utilisateurService.getUtilisateur(idUser);
        reclamation.setUtilisateur(utilisateur);
        reclamation.setDateAjout(new Date());
        reclamation.setIdclient(idUser);
        reclamation = reclamationService.AddReclamation(reclamation);
        reclamationDto = modelMapper.map(reclamation, ReclamationDto.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(reclamationDto);
    }

    @PutMapping("/reclamations/{idRec}")
    public Object UpdateReclamation (@Validated @RequestBody ReclamationDto reclamationDto , @PathVariable Long idRec) {
        Reclamation reclamation = modelMapper.map(reclamationDto,Reclamation.class);
        reclamation.setDateReponse(new Date());
        reclamation= reclamationService.UpdateReclamation(idRec, reclamation);
        reclamationDto = modelMapper.map(reclamation,ReclamationDto.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(reclamationDto);

    }


    @DeleteMapping("/reclamations/{idRec}")
    public Object DeleteReclamation(@PathVariable Long idRec) {
        reclamationService.DeleteReclamation(idRec);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

}
