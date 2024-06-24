package br.com.borges.restaurante.service;

import br.com.borges.restaurante.dao.OrderDishesDao;
import br.com.borges.restaurante.entity.OrderDishes;

import javax.persistence.EntityManager;

public class OrderDishesService {

    public static void saveOrderDishes(EntityManager entityManager, OrderDishes orderDishes){
        OrderDishesDao dao = new OrderDishesDao(entityManager);
        dao.save(orderDishes);
        entityManager.flush();
        entityManager.clear();
    }
}
