package br.com.borges.restaurante.dao;

import br.com.borges.restaurante.entity.OrderDishes;

import javax.persistence.EntityManager;

public class OrderDishesDao {

    private EntityManager entityManager;

    public OrderDishesDao(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    public void save(final OrderDishes orderDishes){
        this.entityManager.persist(orderDishes);
    }

    public OrderDishes findById(final Integer id){
       return this.entityManager.find(OrderDishes.class, id);
    }

    public void update(final OrderDishes orderDishes){
        this.entityManager.merge(orderDishes);
    }

    public void delete(final OrderDishes orderDishes){
        this.entityManager.remove(orderDishes);
    }

}
