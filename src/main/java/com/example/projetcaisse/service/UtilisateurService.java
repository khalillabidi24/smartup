package com.example.projetcaisse.service;

import com.example.projetcaisse.model.entity.Produit;
import com.example.projetcaisse.model.entity.Utilisateur;

import java.util.List;

public interface UtilisateurService {
    Utilisateur getUtilisateur(Long id);
    List<Utilisateur> getUtilisateurList();
    List<Utilisateur> getUtilisateurListActive();
    List<Utilisateur> getUtilisateurListDesactive();
    Utilisateur AddUtilisateur(Utilisateur utilisateur);
    Utilisateur UpdateUtilisateur(Long id,Utilisateur utilisateur);
    void DeleteUtilisateur(Long id);
    Utilisateur activerUtilisateur(Long id);
    Utilisateur desactiverUtilisateur(Long id);
}
