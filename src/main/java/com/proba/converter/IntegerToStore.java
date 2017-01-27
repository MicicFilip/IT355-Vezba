/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proba.converter;

import com.proba.dao.StoreDao;
import com.proba.entity.Category;
import com.proba.entity.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 *
 * @author Micic
 */
@Component
public class IntegerToStore implements Converter<String, Store> {

    @Autowired
    private StoreDao storeDao;
    
    @Override
    public Store convert(String s) {
        if(s.isEmpty()){
            return null;
        }
        Integer valeu = Integer.valueOf(s);
        Store cat = storeDao.getStoreById(valeu);
        return cat;
    }

}
