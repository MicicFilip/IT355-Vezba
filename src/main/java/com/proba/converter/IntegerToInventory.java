/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proba.converter;

import com.proba.dao.InventoryDao;
import com.proba.entity.Inventory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 *
 * @author Micic
 */
@Component
public class IntegerToInventory implements Converter<String, Inventory> {

    @Autowired
    private InventoryDao inventoryDao;
    
    @Override
    public Inventory convert(String s) {
        if(s.isEmpty()){
            return null;
        }
        Integer valeu = Integer.valueOf(s);
        Inventory cat = inventoryDao.getInventoryById(valeu);
        return cat;
    }

}
