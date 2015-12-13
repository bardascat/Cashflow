package com.lab.cashflow.service;

import com.lab.cashflow.domain.Category;
import com.lab.cashflow.dto.CategoryDTO;

import java.util.List;

public interface ICategoryService {

    Category addCategory(CategoryDTO category);

    Category updateCategory(Category category);

    Category getCategory(int id_category);

    void deleteCategory(CategoryDTO categoryDto);

    List<Category> getCategories(int id_parent);

}
