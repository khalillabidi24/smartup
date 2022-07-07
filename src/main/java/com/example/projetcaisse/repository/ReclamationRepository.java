package com.example.projetcaisse.repository;

import com.example.projetcaisse.model.entity.Reclamation;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ReclamationRepository extends CrudRepository<Reclamation,Long> {
    List<Reclamation> findReclamationByUtilisateurId(Long id);
}
