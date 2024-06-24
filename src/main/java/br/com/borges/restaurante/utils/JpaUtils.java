package br.com.borges.restaurante.utils;

import br.com.borges.restaurante.entity.Menu;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaUtils {

    private static EntityManager entityManager;
    public static final EntityManagerFactory RASFOOD = Persistence.createEntityManagerFactory("rasFood");

    public static EntityManager getEntityManager(){
        return RASFOOD.createEntityManager();
    }

}
