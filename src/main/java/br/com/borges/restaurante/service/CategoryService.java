package br.com.borges.restaurante.service;

import br.com.borges.restaurante.dao.CategoryDao;
import br.com.borges.restaurante.entity.Category;

import javax.persistence.EntityManager;

public class CategoryService {

    public static Category saveCategory(EntityManager entityManager, Category category){
        CategoryDao categoryDao = new CategoryDao(entityManager);
        categoryDao.save(category);
        entityManager.flush();
        entityManager.clear();

        return category;
    }
}
