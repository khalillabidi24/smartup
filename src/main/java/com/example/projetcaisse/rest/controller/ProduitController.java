package com.example.projetcaisse.rest.controller;

import com.example.projetcaisse.model.entity.Produit;
import com.example.projetcaisse.repository.ProduitRepository;
import com.example.projetcaisse.rest.dto.ProduitDto;
import com.example.projetcaisse.service.ProduitService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@RestController
@CrossOrigin
public class ProduitController {
    @Autowired
    private ProduitService produitService ;
    @Autowired
    private ModelMapper modelMapper ;

    @Autowired
    ProduitRepository produitRepository ;
    @GetMapping("/produitsActives")
    public Object ProduitlistActive() {
        List<Produit> produits= produitService.getProduitListActive();
        Type listType = new TypeToken<List<ProduitDto>>() {}.getType() ;
        List <ProduitDto> produitDtos= modelMapper.map(produits,listType);
        return ResponseEntity.status(HttpStatus.OK).body(produitDtos);
    }

    @GetMapping("/produitsDesactives")
    public Object ProduitlistDesactive() {
        List<Produit> produits= produitService.getProduitListDesactive();
        Type listType = new TypeToken<List<ProduitDto>>() {}.getType() ;
        List <ProduitDto> produitDtos= modelMapper.map(produits,listType);
        return ResponseEntity.status(HttpStatus.OK).body(produitDtos);
    }
    @GetMapping("/produits/{idProduit}")
    public Object Produit(@PathVariable Long idProduit ) {
        Produit produit = produitService.getProduit(idProduit) ;
        ProduitDto produitDto= modelMapper.map(produit, ProduitDto.class);
        return ResponseEntity.status(HttpStatus.OK).body(produitDto);
    }

    @PostMapping("/produits")
    public Object AddProduit(@Validated @RequestBody ProduitDto produitDto)
    {
        Produit produit = modelMapper.map(produitDto, Produit.class);
        produit = produitService.AddProduit(produit);
        produitDto = modelMapper.map(produit, ProduitDto.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(produitDto);
    }

    @PutMapping("/produits/{idProduit}")
    public Object UpdateProduit (@Validated @RequestBody ProduitDto produitDto , @PathVariable Long idProduit) {
        Produit produit = modelMapper.map(produitDto,Produit.class);
        produit= produitService.UpdateProduit(idProduit, produit);
        produitDto = modelMapper.map(produit,ProduitDto.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(produitDto);

    }

    @PutMapping("/produitsActives/{idProduit}")
    public Object ActiverProduit (@Validated @RequestBody ProduitDto produitDto , @PathVariable Long idProduit) {
        Produit produit = modelMapper.map(produitDto,Produit.class);
        produit= produitService.activerProduit(idProduit);
        produitDto = modelMapper.map(produit,ProduitDto.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(produitDto);

    }

    @PutMapping("/produitsDesactives/{idProduit}")
    public Object DesactiverProduit (@Validated @RequestBody ProduitDto produitDto , @PathVariable Long idProduit) {
        Produit produit = modelMapper.map(produitDto,Produit.class);
        produit= produitService.desactiverProduit(idProduit);
        produitDto = modelMapper.map(produit,ProduitDto.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(produitDto);

    }

    @DeleteMapping("/produits/{idProduit}")
    public Object DeleteProduit(@PathVariable Long idProduit) {
        produitService.DeleteProduit(idProduit);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    @GetMapping("/api/test/produits/getProduitByPromotion")
    public List<Produit> getAllProduitsByPromotion (String startDate, String endDate, float reduction) {

        Logger logger = Logger.getLogger(ProduitController.class.getName());
        LocalDate localDate = LocalDate.now() ;
        logger.info("start date " + startDate + " endDate " + localDate);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate begin = LocalDate.parse(startDate, formatter);


        LocalDate end = LocalDate.parse(endDate, formatter);

        List<Produit> produits = new ArrayList<>() ;
        List<Produit> listProduits = new ArrayList<>() ;
        produits = produitRepository.findAll() ;

        for (int i=0 ; i< produits.size() ;i++) {
            if (localDate.isAfter(begin) && localDate.isBefore(end)) {
                listProduits.add(produits.get(i)) ;
            }
            if (localDate.toString().equals(startDate.toString())) {
                listProduits.add(produits.get(i)) ;
            }
            if (localDate.toString().equals(endDate.toString())) {
                listProduits.add(produits.get(i)) ;
            }
            float x = 0F ;
            if (listProduits != null) {
              x =  listProduits.get(i).getPrix() ;
              x -= reduction ;
              listProduits.get(i).setPrix(x);
            }
        }

        return listProduits ;
    }

}

