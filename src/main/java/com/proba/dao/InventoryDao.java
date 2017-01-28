/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proba.dao;

import com.proba.entity.Inventory;
import java.util.List;

/**
 *
 * @author Micic
 */
public interface InventoryDao {
    
    public List<Inventory> getListInventory();
    
    public Inventory addInventory(Inventory inventory);
    
    public void editInventory(Inventory inventory);
    
    public Inventory getInventoryById(Integer id);
    
    public boolean deleteInventory(Inventory inventory);
}
