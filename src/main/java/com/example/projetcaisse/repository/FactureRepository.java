package com.example.projetcaisse.repository;

import com.example.projetcaisse.model.entity.Facture;
import com.example.projetcaisse.model.entity.Reservation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface FactureRepository extends CrudRepository<Facture,Long> {
    List<Facture> findFactureByDateAjoutBetween(@Param("d1")Date d1,@Param("d2") Date d2);
    List<Facture> findFactureByIdUserAndActive(@Param("id")Long id,@Param("active")int active);
    List<Facture> findFactureByActive(int active);

    @Query("Select count(f) from Facture f where f.active= :type")
    int countFactureByType(@Param("type") Integer type);

}
