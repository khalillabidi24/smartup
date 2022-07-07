package com.example.projetcaisse.repository;


import com.example.projetcaisse.model.entity.Reservation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReservationRepository extends CrudRepository<Reservation,Long> {
    List<Reservation> findReservationByProduitIdProduitAndUtilisateurId(Long idProd,Long idUser);
    List<Reservation> findReservationByUtilisateurIdAndActive(@Param("id")Long id,@Param("active") int active);
    List<Reservation> findReservationByProduitIdProduit(Long idProd);
    List<Reservation> findReservationByFacture_IdFacture(Long IdFacture);
}
