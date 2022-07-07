package com.example.projetcaisse.service;

import com.example.projetcaisse.model.entity.Produit;

import java.util.List;

public interface ProduitService {
        Produit getProduit(Long id);
        List<Produit> getProduitListActive();
        List<Produit> getProduitListDesactive();
        Produit AddProduit(Produit produit);
        Produit UpdateProduit(Long id,Produit produit);
        void DeleteProduit(Long id);
        Produit activerProduit(Long id);
        Produit desactiverProduit(Long id);
    }

