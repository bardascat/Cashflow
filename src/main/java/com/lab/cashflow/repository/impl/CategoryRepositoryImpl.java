package com.lab.cashflow.repository.impl;

import com.lab.cashflow.domain.Category;
import com.lab.cashflow.dto.CategoryDTO;
import com.lab.cashflow.repository.ICategoryRepository;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import java.util.List;

@Transactional
@Repository("CategoryRepository")
public class CategoryRepositoryImpl implements ICategoryRepository {

    private static Logger logger = Logger.getLogger(CategoryRepositoryImpl.class);

    @PersistenceContext
    EntityManager em;

    @Override
    public Category addCategory(CategoryDTO category) {
    	Category newCategory = new Category();
    	if(category.getId_parent()>0){
    		Category parent = this.getCategory(category.getId_parent());
    		newCategory.setParentCategory(parent);
    	}
    	
    	newCategory.setName(category.getName());
        this.em.persist(newCategory);
        return newCategory;
    }

    @Override
    public Category updateCategory(Category category) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void deleteCategory(CategoryDTO categoryDto) {
        
    	Category deleteCategory= this.getCategory(categoryDto.getId_category());
    	this.em.remove(deleteCategory);
    	

    }

    @Override
    public List<Category> getCategories(int id_parent) {

        logger.info("id_parent:" + id_parent);

        String query;

        if (id_parent == 0)
            query = "select c from category c where c.parentCategory is null";
        else
            query = "select c from category c where c.parentCategory.id_category=:id_parent";


        TypedQuery<Category> typeQuery = this.em.createQuery(query, Category.class);

        if (id_parent > 0)
            typeQuery.setParameter("id_parent", id_parent);

        List<Category> result = typeQuery.getResultList();
        return result;
    }

    @Override
    public Category getCategory(int id_category) {
        Category cat = this.em.find(Category.class, id_category);
        return cat;
    }


}
