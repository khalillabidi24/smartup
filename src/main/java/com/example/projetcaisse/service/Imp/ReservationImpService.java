package com.example.projetcaisse.service.Imp;

import com.example.projetcaisse.model.entity.Produit;
import com.example.projetcaisse.model.entity.Reservation;
import com.example.projetcaisse.repository.ProduitRepository;
import com.example.projetcaisse.repository.ReservationRepository;
import com.example.projetcaisse.service.ProduitService;
import com.example.projetcaisse.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationImpService implements ReservationService {
    @Autowired
    private ReservationRepository reservationRepository ;
    @Autowired
    private ProduitRepository produitRepository ;
    @Autowired
    private ProduitService produitService;

    @Override
    public Reservation getReservation(Long id) {
        return reservationRepository.findById(id).get();
    }

    @Override
    public List<Reservation> getReservationListParUserandNonConfirme(Long idUser) {
        return (List<Reservation>)reservationRepository.findReservationByUtilisateurIdAndActive(idUser,0);
    }
    @Override
    public List<Reservation> getReservationListParUserandConfirme(Long idUser) {
        return (List<Reservation>)reservationRepository.findReservationByUtilisateurIdAndActive(idUser,1);
    }
    @Override
    public List<Reservation> getReservationByFacture(Long idFacture) {
        return (List<Reservation>)reservationRepository.findReservationByFacture_IdFacture(idFacture);
    }
    @Override
    public List<Reservation> getReservationListParProduit(Long idProd) {
        return (List<Reservation>)reservationRepository.findReservationByProduitIdProduit(idProd);
    }

    @Override
    public List<Reservation> getReservationL() {
        return (List<Reservation>)reservationRepository.findAll();
    }

    @Override
    public List<Reservation> getReservationList(Long idProd,Long idUser) {
        return (List<Reservation>)reservationRepository.findReservationByProduitIdProduitAndUtilisateurId(idProd,idUser);
    }


    @Override
    public Reservation AddReservation(Reservation reservation,Long idP) {
        Produit p = produitService.getProduit(idP);
        reservation.setLibelle(p.getLibelle());
        reservation.setPrix(p.getPrix());
        reservation.setQtefinal(p.getQte());
        int a =0;
        List<Reservation> reservations=reservationRepository.findReservationByUtilisateurIdAndActive(reservation.getUtilisateur().getId(),0);
        if (reservations != null) {
            for (Reservation r : reservations) {
                if (reservation.getProduit().getIdProduit() == r.getProduit().getIdProduit() && reservation.getUtilisateur().getId() == r.getUtilisateur().getId()) {
                    reservation.setQtefinal(r.getQtefinal());
                    reservation.setQte(reservation.getQte() + r.getQte());
                    reservationRepository.deleteById(r.getIdReservation());
                }
            }}
                p.setQte(reservation.getQtefinal() - reservation.getQte());
                p.setIdProduit(idP);
                produitRepository.save(p);

        return reservationRepository.save(reservation);
    }

    @Override
    public Reservation UpdateReservation(Long id, Reservation reservation,Long idProd) {
        getReservation(id);
        Produit p=produitService.getProduit(idProd);
        reservation.setQtefinal(getReservation(id).getQtefinal());
        p.setQte(reservation.getQtefinal()-reservation.getQte());
        p.setIdProduit(idProd);
        produitRepository.save(p);
         reservation.setIdReservation(id);
        return reservationRepository.save(reservation);
    }

    @Override
    public void DeleteReservation(Long id) {
        Produit p=getReservation(id).getProduit();
        p.setQte(p.getQte()+getReservation(id).getQte());
        p.setIdProduit(p.getIdProduit());
        produitRepository.save(p);
        reservationRepository.deleteById(id);
    }
}
