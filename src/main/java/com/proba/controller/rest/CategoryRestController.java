/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proba.controller.rest;

import com.proba.dao.CategoryDao;
import com.proba.entity.Category;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Micic
 */
@RestController
public class CategoryRestController {

    @Autowired
    private CategoryDao categoryDao;

    @RequestMapping(value = "/categories", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Category>> getCategories() {
        System.out.println("Fetching categories");
        List<Category> categories = categoryDao.getListCategory();
        if (categories.size() == 0) {
            System.out.println("Categories list is empty");
            return new ResponseEntity<List<Category>>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Category>>(categories, HttpStatus.OK);
    }

    @RequestMapping(value = "/category/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Category> getCategoryById(@PathVariable("id") int id) {
        System.out.println("Fetching category with id:" + id);
        Category category = categoryDao.getCategoryById(id);
        if (category == null) {
            System.out.println("Category with id " + id + " not found");
            return new ResponseEntity<Category>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Category>(category, HttpStatus.OK);
    }

    @RequestMapping(value = "/category/", method = RequestMethod.POST)
    public ResponseEntity<Void> addCategory(@RequestBody Category category) {
        System.out.println("Adding category " + category.toString());
        categoryDao.addCategory(category);

        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
    
    @RequestMapping(value = "/category/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Category> editCategory(@PathVariable("id") int id, @RequestBody Category category){
        System.out.println("Updating category " + id);
        
        category.setId(id);
        categoryDao.editCategory(category);
        return new ResponseEntity<Category>(category, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/category/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Category> deleteCategory(@PathVariable("id") int id){
        System.out.println("Fetching & Deleting category with id " + id);
        
        Category category = categoryDao.getCategoryById(id);
        if(category == null){
            System.out.println("Unable to delete. Category with id:" + id + "not found");
            return new ResponseEntity<Category>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Category>(HttpStatus.OK);
    }
}
