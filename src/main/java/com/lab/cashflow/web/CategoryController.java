package com.lab.cashflow.web;

import com.lab.cashflow.domain.Category;
import com.lab.cashflow.dto.CategoryDTO;
import com.lab.cashflow.service.ICategoryService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CategoryController {

    private static final Logger logger = Logger.getLogger(CategoryController.class);

    @Autowired
    ICategoryService categoryService;



    @RequestMapping(value = "/category/get-categories", method = RequestMethod.GET)
    @ResponseBody
    public List<Category> getRootCategories(@RequestParam(value = "id_parent", required = false) Integer id_parent_param) {

        int id_parent = (id_parent_param == null ? 0 : id_parent_param);

        logger.info("ID_PARENT:" + id_parent);

        List<Category> categories = this.categoryService.getCategories(id_parent);


        logger.info("getRootCategories - Found " + categories.size() + " categories");

        return categories;
    }
    
    @RequestMapping(value="/category/add-category",method=RequestMethod.POST)
    @ResponseBody
    public Category addCategory(@RequestBody CategoryDTO newCategory){
    	
    	logger.info(newCategory.toString());
    	Category cat = this.categoryService.addCategory(newCategory);
    	return cat;
    	
    }
    
    
    
    @RequestMapping(value="/category/delete-category",method=RequestMethod.POST)
    public ResponseEntity<String> deleteCategory(@RequestBody CategoryDTO oldCategory){
    	
    	try{
    	this.categoryService.deleteCategory(oldCategory);
    		return new ResponseEntity<String>(HttpStatus.OK);
    	}catch(Exception e){
    		return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
    	}
    	
    }


}
