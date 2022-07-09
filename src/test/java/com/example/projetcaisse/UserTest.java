package com.example.projetcaisse;



import com.example.projetcaisse.model.entity.Utilisateur;
import com.example.projetcaisse.repository.UtilisateurRepository;
import com.example.projetcaisse.service.Imp.UtilisateurImpService;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.expression.ParseException;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import java.util.List;





@SpringBootTest

     class UserTest {
    //private static final Logger l = LogManager.getLogger(UtilisateurImpService.class);
    private static final Logger L = LogManager.getLogger();
    @Autowired
    UtilisateurImpService us;


    @Autowired
    UtilisateurRepository ur;

    @Test
    void addUserTest() throws ParseException {


        Utilisateur u = new Utilisateur();
        u.getUsername();
        L.log(Level.INFO, () -> "Add user : " + u);


    }



    @Test
    public void testListUser() {
        List<Utilisateur> e = us.getUtilisateurList();

        assertThat(e).size().isPositive();
        if (e.isEmpty()) {
            L.info("user list active ");
        } else {
            L.info("user list desactive");
        }
    }






}
