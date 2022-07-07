package com.example.projetcaisse.service.Imp;

import com.example.projetcaisse.model.entity.Facture;
import com.example.projetcaisse.model.entity.Reservation;
import com.example.projetcaisse.model.entity.Utilisateur;
import com.example.projetcaisse.repository.FactureRepository;
import com.example.projetcaisse.repository.ReservationRepository;
import com.example.projetcaisse.service.FactureService;
import com.example.projetcaisse.service.ReservationService;
import com.example.projetcaisse.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
public class FactureImpService implements FactureService {

    @Autowired
    private FactureRepository repository ;
    @Autowired
    private ReservationService reservationService ;
    @Autowired
    private ReservationRepository reservationRepository ;
    @Autowired
    private UtilisateurService utilisateurService;

    @Override
    public Facture getFacture(Long id) {
        return repository.findById(id).get();
    }

    @Override
    public List<Facture> getFacturesTraite() {
        return (List<Facture>) repository.findFactureByActive(1);
    }
    @Override
    public List<Facture> getFacturesEnCour() {
        return (List<Facture>) repository.findFactureByActive(0);
    }

    @Override
    public List<Facture> getFactureRecuByClient(Long id) {
        return (List<Facture>) repository.findFactureByIdUserAndActive(id,1);
    }
    @Override
    public List<Facture> getFactureEnCourByClient(Long id) {
        return (List<Facture>) repository.findFactureByIdUserAndActive(id,0);
    }

    @Override
    public List<Facture> getFactureByDates(Date d1, Date d2) {
        return (List<Facture>) repository.findFactureByDateAjoutBetween(d1,d2);    }

    @Override
    public Facture AddFacture(Facture facture,Long idUser) throws ParseException {
        facture.setIdUser(idUser);
        Utilisateur u =utilisateurService.getUtilisateur(idUser);
        facture.setUsername(u.getUsername());
        facture.setAdresse(u.getAdresse());
        facture.setTelephone(u.getTelephone());
        facture.setReservations(reservationService.getReservationListParUserandNonConfirme(idUser));
        List<Reservation> reservations = facture.getReservations();
        facture.setPrixSansTVA(0f);
        facture=repository.save(facture);
        Long id=facture.getIdFacture();
        if (reservations != null) {
            for (Reservation reservation : reservations) {
                facture.setPrixSansTVA(facture.getPrixSansTVA()+ (reservation.getPrix()* reservation.getQte()));
                reservation.setActive(1);
                reservation.setFacture(facture);
                reservation.setIdReservation(reservation.getIdReservation());
                reservationRepository.save(reservation);
                //reservationService.DeleteReservation(reservation.getIdReservation());
            }
        }
        facture.setTva((float) (facture.getPrixSansTVA()*0.1));
        facture.setPrixCompriseTVA(facture.getPrixSansTVA() + facture.getTva());
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(new Date());
        Date date1=new SimpleDateFormat("yyyy-MM-dd").parse(date);
        facture.setDateAjout(date1);

        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        String generatedString = buffer.toString();
        facture.setCode(generatedString);
        facture.setIdFacture(id);
        return repository.save(facture);
    }

    @Override
    public Facture UpdateFacture(Long id, Facture facture) {
        return null;
    }

    @Override
    public void DeleteFacture(Long id) {
        List<Reservation> reservations = getFacture(id).getReservations();
        if (reservations != null) {
            for (Reservation reservation : reservations) {
                reservationService.DeleteReservation(reservation.getIdReservation());
            }
        }
        repository.deleteById(id);
    }

    @Override
    public Facture activerFacture(Long id) {
        Facture f=repository.findById(id).get();
        f.setActive(1);
        return repository.save(f);    }

    @Override
    public int countFactureByType(Integer type) {
       return this.repository.countFactureByType(type);
    }


}
