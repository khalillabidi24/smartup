package com.example.projetcaisse.service.Imp;

import com.example.projetcaisse.model.entity.Reclamation;
import com.example.projetcaisse.repository.ReclamationRepository;
import com.example.projetcaisse.service.ReclamationService;
import com.example.projetcaisse.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class ReclamationImpService implements ReclamationService {
    @Autowired
    private UtilisateurService utilisateurService ;
    @Autowired
    private ReclamationRepository reclamationRepository ;
    @Override
    public Reclamation getReclamation(Long id) {
        return reclamationRepository.findById(id).get();
    }

    @Override
    public List<Reclamation> getReclamationList(Long idUser) {
        return (List<Reclamation>)reclamationRepository.findReclamationByUtilisateurId(idUser);
    }
    @Override
    public List<Reclamation> getReclamationListAll() {
        return (List<Reclamation>)reclamationRepository.findAll();
    }

    @Override
    public Reclamation AddReclamation(Reclamation reclamation) {
        reclamation.setConfirmation("n'est pas encore validé vous recevrez une reponse ulterieurement");
        return reclamationRepository.save(reclamation);
    }

    @Override
    public Reclamation UpdateReclamation(Long id, Reclamation reclamation) {
       Reclamation r= getReclamation(id);
        reclamation.setUtilisateur(utilisateurService.getUtilisateur(r.getUtilisateur().getId()));
        reclamation.setDateAjout(getReclamation(id).getDateAjout());
        reclamation.setIdRec(id);
        return reclamationRepository.save(reclamation);
    }

    @Override
    public void DeleteReclamation(Long id) {
        reclamationRepository.deleteById(id);
    }
}
