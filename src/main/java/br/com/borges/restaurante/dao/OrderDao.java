package br.com.borges.restaurante.dao;

import br.com.borges.restaurante.entity.Order;
import br.com.borges.restaurante.entity.Order;

import javax.persistence.EntityManager;

public class OrderDao {

    private EntityManager entityManager;

    public OrderDao(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    public void save(final Order order){
        this.entityManager.persist(order);
    }

    public Order findById(final Integer id){
       return this.entityManager.find(Order.class, id);
    }

    public void update(final Order order){
        this.entityManager.merge(order);
    }

    public void delete(final Order order){
        this.entityManager.remove(order);
    }

}
