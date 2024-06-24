package br.com.borges.restaurante.service;

import br.com.borges.restaurante.dao.MenuDao;
import br.com.borges.restaurante.entity.Category;
import br.com.borges.restaurante.entity.Menu;
import br.com.borges.restaurante.utils.JpaUtils;

import javax.persistence.EntityManager;
import java.math.BigDecimal;

public class MenuService {
    public static void saveMenu(EntityManager entityManager, Menu menu){

        MenuDao menuDao = new MenuDao(entityManager);
        menuDao.save(menu);
        entityManager.flush();
        entityManager.clear();

    }
}
