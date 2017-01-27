/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proba.controller;

import com.proba.dao.StoreDao;
import com.proba.entity.Store;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Micic
 */
@Controller
public class StoreController {
    
    @Autowired
    private StoreDao storeDao;
    
    @RequestMapping(value = "/addStore", method = RequestMethod.GET)
    public String addStore(Model model){
        model.addAttribute("store", new Store());
        List stores = storeDao.getListStore();
        model.addAttribute("stores", stores);
        return "addStore";
    }
    
    @RequestMapping(value = "/addStore", method = RequestMethod.POST)
    public ModelAndView addStorePost(@ModelAttribute("store") Store store, ModelAndView model){
        store = storeDao.addStore(store);
        model.addObject("successMsg", "Store added");
        model.addObject("store", new Store());
        
        List stores = storeDao.getListStore();
        model.addObject("stores", stores);
        return model;
    }
    
    @RequestMapping(value = "/deleteStore/{id}", method = RequestMethod.GET)
    public String deleteStore(@PathVariable("id") int id, HttpServletRequest request){
        Store store = storeDao.getStoreById(id);
        if(store == null){
            String referer = request.getHeader("Referer");
            return "redirect:" + referer;
        }
        storeDao.deleteStore(store);
        String referer = request.getHeader("Referer");
        return "redirect:" + referer;
    }
    
    @RequestMapping(value = "/editStore/{id}", method = RequestMethod.GET)
    public String editStore(@PathVariable("id") int id, Model model){
        Store store = storeDao.getStoreById(id);
        model.addAttribute("store", store);
        
        List stores = storeDao.getListStore();
        model.addAttribute("stores", stores);
        return "addStore";
    }
}
