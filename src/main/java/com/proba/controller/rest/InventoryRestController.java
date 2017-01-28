/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proba.controller.rest;

import com.proba.dao.InventoryDao;
import com.proba.entity.Inventory;
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
public class InventoryRestController {

    @Autowired
    private InventoryDao inventoryDao;

    @RequestMapping(value = "/inventories", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Inventory>> getInventories() {
        System.out.println("Fetching inventories");

        List<Inventory> inventory = inventoryDao.getListInventory();
        if (inventory.size() == 0) {
            System.out.println("Inventories list is empty");
            return new ResponseEntity<List<Inventory>>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<List<Inventory>>(inventory, HttpStatus.OK);
    }

    @RequestMapping(value = "/inventory/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Inventory> getInventoryById(@PathVariable("id") int id) {
        System.out.println("Fetching inventory with id: " + id);

        Inventory inventory = inventoryDao.getInventoryById(id);
        if (inventory == null) {
            System.out.println("Inventory with id: " + id + " is not found");

            return new ResponseEntity<Inventory>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Inventory>(inventory, HttpStatus.OK);
    }

    @RequestMapping(value = "/inventory/", method = RequestMethod.POST)
    public ResponseEntity<Void> addInventory(@RequestBody Inventory inventory) {
        System.out.println("Adding inventory " + inventory.toString());
        inventoryDao.addInventory(inventory);

        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/inventory/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Inventory> editInventory(@PathVariable("id") int id, @RequestBody Inventory inventory) {
        System.out.println("Updating inventory " + id);

        inventory.setId(id);
        inventoryDao.editInventory(inventory);
        return new ResponseEntity<Inventory>(inventory, HttpStatus.OK);
    }

    @RequestMapping(value = "/inventory/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Inventory> deleteInventory(@PathVariable("id") int id) {
        System.out.println("Fetching & Deleting inventory with id " + id);

        Inventory inventory = inventoryDao.getInventoryById(id);
        if (inventory == null) {
            System.out.println("Unable to delete. inventory with id:" + id + "not found");
            return new ResponseEntity<Inventory>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Inventory>(HttpStatus.OK);
    }
}
