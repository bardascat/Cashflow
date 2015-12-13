package com.lab.cashflow.repository;

import java.util.List;

import com.lab.cashflow.domain.Category;
import com.lab.cashflow.dto.CategoryDTO;

public interface ICategoryRepository {

    Category addCategory(CategoryDTO category);
    void deleteCategory(CategoryDTO categoryDto);

    Category updateCategory(Category category);

    List<Category> getCategories(int id_parent);

    Category getCategory(int id_category);

}
