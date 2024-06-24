package br.com.borges.restaurante.dao;

import br.com.borges.restaurante.entity.Menu;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class MenuDao {

    private EntityManager entityManager;

    public MenuDao(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    public void save(final Menu menu){
        this.entityManager.persist(menu);
    }

    public Menu findById(final Integer id){
       return this.entityManager.find(Menu.class, id);
    }

    public void update(final Menu menu){
        this.entityManager.merge(menu);
    }

    public void delete(final Menu menu){
        this.entityManager.remove(menu);
    }

    public List<Menu> findAll(){
        String jpql = "SELECT m FROM Menu m";
        return entityManager
                .createQuery(jpql, Menu.class)
                .getResultList();
    }

    public List<Menu> findByDishValue(BigDecimal filter){
        String jpql = "SELECT m FROM Menu m WHERE m.valueDish = :value";
        return entityManager
                .createQuery(jpql, Menu.class)
                .setParameter("value", filter)
                .getResultList();
    }

    public List<Menu> findByLikeDishName(String filter){
        String jpql = "SELECT m FROM Menu m WHERE m.name like '% :value %'";
        return entityManager
                .createQuery(jpql, Menu.class)
                .setParameter("value", filter)
                .getResultList();
    }

    public Menu findByDishName(String filter){

        try {
            String jpql = "SELECT m FROM Menu m WHERE UPPER(m.name) = UPPER(:value) ";
            return entityManager
                    .createQuery(jpql, Menu.class)
                    .setParameter("value", filter)
                    .getSingleResult();

        }catch (Exception e){
            return null;
        }
    }
}
