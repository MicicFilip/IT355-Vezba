/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proba.dao;

import com.proba.entity.Category;
import java.util.List;

/**
 *
 * @author Filip
 */
public interface CategoryDao {
    
    public List<Category> getListCategory();
    
    public Category addCategory(Category category);
    
    public void editCategory(Category category);
    
    public Category getCategoryById(Integer id);
    
    public boolean deleteCategory(Category category);
}
