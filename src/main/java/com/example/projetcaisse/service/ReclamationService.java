package com.example.projetcaisse.service;

import com.example.projetcaisse.model.entity.Produit;
import com.example.projetcaisse.model.entity.Reclamation;

import java.util.List;

public interface ReclamationService {
    Reclamation getReclamation(Long id);
    List<Reclamation> getReclamationList(Long idUser);
    Reclamation AddReclamation(Reclamation reclamation);
    Reclamation UpdateReclamation(Long id,Reclamation reclamation);
    void DeleteReclamation(Long id);
    List<Reclamation> getReclamationListAll();
}
