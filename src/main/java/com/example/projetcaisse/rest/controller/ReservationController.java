package com.example.projetcaisse.rest.controller;

import com.example.projetcaisse.model.entity.Produit;
import com.example.projetcaisse.model.entity.Reservation;
import com.example.projetcaisse.model.entity.Utilisateur;
import com.example.projetcaisse.rest.dto.ReservationDto;
import com.example.projetcaisse.service.ProduitService;
import com.example.projetcaisse.service.ReservationService;
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
public class ReservationController {

    @Autowired
    private UtilisateurService utilisateurService;

    @Autowired
    private ProduitService produitService;

    @Autowired
    private ReservationService reservationService;

    /**
     * Model Mapper
     */
    @Autowired
    private ModelMapper modelMapper;


    @GetMapping("/utilisateur/{idUser}/produit/{idProd}/reservation")
    public Object ReservationList(@PathVariable long idUser, @PathVariable long idProd) {
        List<Reservation> reservations = reservationService.getReservationList(idProd, idUser);
        Type listType = new TypeToken<List<ReservationDto>>() {
        }.getType();
        List<ReservationDto> reservationDtos = modelMapper.map(reservations, listType);
        return ResponseEntity.status(HttpStatus.OK).body(reservationDtos);
    }

    @GetMapping("/reservation")
    public Object ReservationL() {
        List<Reservation> reservations = reservationService.getReservationL();
        Type listType = new TypeToken<List<ReservationDto>>() {}.getType();
        List<ReservationDto> reservationDtos = modelMapper.map(reservations, listType);
        return ResponseEntity.status(HttpStatus.OK).body(reservationDtos);
    }

    @GetMapping("/utilisateur/{idUser}/reservation")
    public Object ReservationListUsernonConfirme(@PathVariable long idUser) {
        List<Reservation> reservations = reservationService.getReservationListParUserandNonConfirme(idUser);
        Type listType = new TypeToken<List<ReservationDto>>() {
        }.getType();
        List<ReservationDto> reservationDtos = modelMapper.map(reservations, listType);
        return ResponseEntity.status(HttpStatus.OK).body(reservationDtos);
    }
    @GetMapping("/utilisateur/{idUser}/reservationConfirme")
    public Object ReservationListUserConfirme(@PathVariable long idUser) {
        List<Reservation> reservations = reservationService.getReservationListParUserandConfirme(idUser);
        Type listType = new TypeToken<List<ReservationDto>>() {
        }.getType();
        List<ReservationDto> reservationDtos = modelMapper.map(reservations, listType);
        return ResponseEntity.status(HttpStatus.OK).body(reservationDtos);
    }
    @GetMapping("/facture/{idFacture}/reservation")
    public Object ReservationListFacture(@PathVariable long idFacture) {
        List<Reservation> reservations = reservationService.getReservationByFacture(idFacture);
        Type listType = new TypeToken<List<ReservationDto>>() {
        }.getType();
        List<ReservationDto> reservationDtos = modelMapper.map(reservations, listType);
        return ResponseEntity.status(HttpStatus.OK).body(reservationDtos);
    }

    @GetMapping("/produit/{idProd}/reservation")
    public Object ReservationListProd(@PathVariable long idProd) {
        List<Reservation> reservations = reservationService.getReservationListParProduit(idProd);
        Type listType = new TypeToken<List<ReservationDto>>() {}.getType();
        List<ReservationDto> reservationDtos = modelMapper.map(reservations, listType);
        return ResponseEntity.status(HttpStatus.OK).body(reservationDtos);
    }


    @GetMapping("/reservation/{id}")
    public Object retrieveReservation(@PathVariable long id) {
        Reservation reservation = reservationService.getReservation(id);
        ReservationDto reservationDto = modelMapper.map(reservation, ReservationDto.class);
        return ResponseEntity.status(HttpStatus.OK).body(reservationDto);
    }

    @PostMapping("/utilisateur/{idUser}/produit/{idProd}/reservation")
    public Object addReservation(@PathVariable long idUser, @PathVariable long idProd, @Validated @RequestBody ReservationDto reservationDto) {
        Reservation reservation = modelMapper.map(reservationDto, Reservation.class);
        Utilisateur utilisateur = utilisateurService.getUtilisateur(idUser);
        Produit produit = produitService.getProduit(idProd);
        reservation.setUtilisateur(utilisateur);
        reservation.setProduit(produit);
        reservation.setDateAjout(new Date());
        reservation.setIdClient(utilisateur.getId());
        reservationService.AddReservation(reservation,idProd);
        reservationDto = modelMapper.map(reservation, ReservationDto.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(reservationDto);
    }

    @PutMapping("/utilisateur/{idUser}/produit/{idProd}/reservation/{idRes}")
    public Object updateReservation(@PathVariable long idUser, @PathVariable long idProd,
                                    @Validated @RequestBody ReservationDto reservationDto,
                                    @PathVariable long idRes) {
        Reservation reservation = modelMapper.map(reservationDto, Reservation.class);
        Utilisateur utilisateur = utilisateurService.getUtilisateur(idUser);
        Produit produit = produitService.getProduit(idProd);
        reservation.setUtilisateur(utilisateur);
        reservation.setProduit(produit);
        reservation.setIdReservation(idRes);
        reservation.setLibelle(produit.getLibelle());
        reservation.setPrix(produit.getPrix());
        reservationService.UpdateReservation(idRes,reservation,idProd);
        reservationDto = modelMapper.map(reservation, ReservationDto.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(reservationDto);
    }


    @DeleteMapping("/reservation/{idRes}")
    public Object Delete(@PathVariable Long idRes) {
        reservationService.DeleteReservation(idRes);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}