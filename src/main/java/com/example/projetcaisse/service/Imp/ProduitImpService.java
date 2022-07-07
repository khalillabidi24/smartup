package com.example.projetcaisse.service.Imp;

import com.example.projetcaisse.model.entity.Produit;
import com.example.projetcaisse.repository.ProduitRepository;
import com.example.projetcaisse.service.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ProduitImpService implements ProduitService {
    @Autowired
    private ProduitRepository produitRepos ;
    @Override
    public Produit getProduit(Long id) {
        return produitRepos.findById(id).get();
    }

    @Override
    public List<Produit> getProduitListActive() {
        return (List<Produit>)produitRepos.findProduitByActive(1);
    }

    @Override
    public List<Produit> getProduitListDesactive() {
        return (List<Produit>)produitRepos.findProduitByActive(0);
    }

    @Override
    public Produit AddProduit(Produit produit) {
        produit.setDateAjout(new Date());
        produit.setActive(1);
        return produitRepos.save(produit);
    }

    @Override
    public Produit UpdateProduit(Long id, Produit produit) {
        getProduit(id);
        produit.setIdProduit(id);
        return produitRepos.save(produit);
    }

    @Override
    public void DeleteProduit(Long id) {
        produitRepos.deleteById(id);
    }

    @Override
    public Produit activerProduit(Long id) {
        Produit p=produitRepos.findById(id).get();
        p.setActive(1);
        return produitRepos.save(p);
    }

    @Override
    public Produit desactiverProduit(Long id) {
        Produit p=produitRepos.findById(id).get();
        p.setActive(0);
        return produitRepos.save(p);
    }
}
