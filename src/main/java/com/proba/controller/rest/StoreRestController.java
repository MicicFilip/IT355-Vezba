/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proba.controller.rest;

import com.proba.dao.StoreDao;
import com.proba.entity.Store;
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
public class StoreRestController {

    @Autowired
    private StoreDao storeDao;

    @RequestMapping(value = "/stores", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Store>> getStores() {
        System.out.println("Fetching stores");

        List<Store> stores = storeDao.getListStore();
        if (stores.size() == 0) {
            System.out.println("Stores list is empty");
            return new ResponseEntity<List<Store>>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<List<Store>>(stores, HttpStatus.OK);
    }

    @RequestMapping(value = "/store/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Store> getStoreById(@PathVariable("id") int id) {
        System.out.println("Fetching store with id " + id);

        Store store = storeDao.getStoreById(id);
        if (store == null) {
            System.out.println("Store with id " + id + " not found");
            return new ResponseEntity<Store>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Store>(store, HttpStatus.OK);
    }

    @RequestMapping(value = "/store/", method = RequestMethod.POST)
    public ResponseEntity<Void> addStore(@RequestBody Store store) {
        System.out.println("Adding store " + store.toString());
        storeDao.addStore(store);

        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/store/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Store> editStore(@PathVariable("id") int id, @RequestBody Store store) {
        System.out.println("Updating store " + id);

        store.setId(id);
        storeDao.editStore(store);
        return new ResponseEntity<Store>(store, HttpStatus.OK);
    }

    @RequestMapping(value = "/store/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Store> deleteStore(@PathVariable("id") int id) {
        System.out.println("Fetching & Deleting store with id " + id);

        Store store = storeDao.getStoreById(id);
        if (store == null) {
            System.out.println("Unable to delete. Store with id:" + id + "not found");
            return new ResponseEntity<Store>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Store>(HttpStatus.OK);
    }
}
