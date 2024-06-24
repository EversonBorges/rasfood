package br.com.borges.restaurante.dao;

import br.com.borges.restaurante.entity.Client;

import javax.persistence.EntityManager;

public class ClientDao {

    private EntityManager entityManager;

    public ClientDao(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    public void save(final Client client){
        this.entityManager.persist(client);
    }

    public Client findById(final Integer id){
       return this.entityManager.find(Client.class, id);
    }

    public void update(final Client client){
        this.entityManager.merge(client);
    }

    public void delete(final Client client){
        this.entityManager.remove(client);
    }

}
