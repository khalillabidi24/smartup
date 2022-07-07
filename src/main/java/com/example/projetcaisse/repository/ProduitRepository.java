package com.example.projetcaisse.repository;

import com.example.projetcaisse.model.entity.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProduitRepository extends JpaRepository<Produit,Long> {
    List<Produit> findProduitByActive(@Param("active") int active);
}
