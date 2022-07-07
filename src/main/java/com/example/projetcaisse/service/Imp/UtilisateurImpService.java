package com.example.projetcaisse.service.Imp;


import com.example.projetcaisse.model.entity.Utilisateur;
import com.example.projetcaisse.repository.UtilisateurRepository;
import com.example.projetcaisse.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UtilisateurImpService implements UtilisateurService {
    @Autowired
    private UtilisateurRepository utilisateurRepository ;
    @Override
    public Utilisateur getUtilisateur(Long id) {
        return utilisateurRepository.findById(id).get();
    }


    @Override
    public List<Utilisateur> getUtilisateurList() {
        return (List<Utilisateur>)utilisateurRepository.findAll();
    }

    @Override
    public List<Utilisateur> getUtilisateurListActive() {
        return (List<Utilisateur>)utilisateurRepository.findUtilisateurByActiveAndUsernameIsNotLike(1,"admin");
    }

    @Override
    public List<Utilisateur> getUtilisateurListDesactive() {
        return (List<Utilisateur>)utilisateurRepository.findUtilisateurByActiveAndUsernameIsNotLike(0,"admin");
    }

    @Override
    public Utilisateur AddUtilisateur(Utilisateur user) {
        user.setActive(1);
        return utilisateurRepository.save(user);
    }

    @Override
    public Utilisateur UpdateUtilisateur(Long id, Utilisateur utilisateur) {
        Utilisateur u=getUtilisateur(id);
        utilisateur.setPassword(u.getPassword());
        utilisateur.setRoles(u.getRoles());
        utilisateur.setId(id);
        return utilisateurRepository.save(utilisateur);
    }
    @Override
    public Utilisateur activerUtilisateur(Long id) {
        Utilisateur p=utilisateurRepository.findById(id).get();
        p.setActive(1);
        return utilisateurRepository.save(p);
    }

    @Override
    public Utilisateur desactiverUtilisateur(Long id) {
        Utilisateur p=utilisateurRepository.findById(id).get();
        p.setActive(0);
        return utilisateurRepository.save(p);
    }
    @Override
    public void DeleteUtilisateur(Long id) {
        utilisateurRepository.deleteById(id);
    }
}