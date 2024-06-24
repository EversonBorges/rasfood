package br.com.borges.restaurante.dao;

import br.com.borges.restaurante.entity.Category;

import javax.persistence.EntityManager;

public class CategoryDao {

    private EntityManager entityManager;

    public CategoryDao(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    public void save(final Category category){
        this.entityManager.persist(category);
    }

    public Category findById(final Integer id){
       return this.entityManager.find(Category.class, id);
    }

    public void update(final Category category){
        this.entityManager.merge(category);
    }

    public void delete(final Category category){
        this.entityManager.remove(category);
    }

}
