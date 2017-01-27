/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proba.converter;

import com.proba.dao.CategoryDao;
import com.proba.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 *
 * @author Filip
 */
@Component
public class IntegerToCategory implements Converter<String , Category>{
    
    @Autowired
    CategoryDao categoryDao;

    @Override
    public Category convert(String s) {
        if(s.isEmpty()){
            return null;
        }
        Integer valeu = Integer.valueOf(s);
        Category cat = categoryDao.getCategoryById(valeu);
        return cat;
    }
}
