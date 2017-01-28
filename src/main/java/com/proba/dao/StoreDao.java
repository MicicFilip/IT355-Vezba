/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proba.dao;

import com.proba.entity.Store;
import java.util.List;

/**
 *
 * @author Micic
 */
public interface StoreDao {
    
    public List<Store> getListStore();
    
    public Store addStore(Store store);
    
    public void editStore(Store store);
    
    public Store getStoreById(Integer id);
    
    public boolean deleteStore(Store store);
}
