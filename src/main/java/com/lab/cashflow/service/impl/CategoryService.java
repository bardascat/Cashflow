package com.lab.cashflow.service.impl;

import com.lab.cashflow.domain.Category;
import com.lab.cashflow.dto.CategoryDTO;
import com.lab.cashflow.repository.ICategoryRepository;
import com.lab.cashflow.service.ICategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("CategoryService")
public class CategoryService implements ICategoryService {

    @Autowired
    ICategoryRepository categoryRepository;

    @Override
    public Category addCategory(CategoryDTO category) {
        return categoryRepository.addCategory(category);
    }

    @Override
    public Category updateCategory(Category category) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void deleteCategory(CategoryDTO categoryDto) {
        	this.categoryRepository.deleteCategory(categoryDto);

    }

    @Override
    public List<Category> getCategories(int id_parent) {

        return this.categoryRepository.getCategories(id_parent);

    }

    @Override
    public Category getCategory(int id_category) {
        return this.categoryRepository.getCategory(id_category);
    }


}
