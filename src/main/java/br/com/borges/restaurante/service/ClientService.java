package br.com.borges.restaurante.service;

import br.com.borges.restaurante.dao.ClientDao;
import br.com.borges.restaurante.entity.Client;

import javax.persistence.EntityManager;

public class ClientService {

    public static Client saveClient(EntityManager entityManager, Client client){
        ClientDao dao = new ClientDao(entityManager);
        dao.save(client);
        entityManager.flush();
        entityManager.clear();

        return client;
    }
}
