package br.com.borges.restaurante.service;

import br.com.borges.restaurante.dao.OrderDao;
import br.com.borges.restaurante.entity.Client;
import br.com.borges.restaurante.entity.Order;

import javax.persistence.EntityManager;

public class OrderService {

    public static Order createOrder(EntityManager entityManager, Client client){
        Order order = new Order(client);
        OrderDao dao = new OrderDao(entityManager);
        dao.save(order);
        entityManager.flush();
        entityManager.clear();

        return order;
    }
}
