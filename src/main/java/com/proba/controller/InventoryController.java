/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proba.controller;

import com.proba.dao.CategoryDao;
import com.proba.dao.InstrumentDao;
import com.proba.dao.InventoryDao;
import com.proba.dao.StoreDao;
import com.proba.entity.Inventory;
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
public class InventoryController {

    @Autowired
    private CategoryDao categoryDao;

    @Autowired
    private InstrumentDao instrumentDao;

    @Autowired
    private StoreDao storeDao;

    @Autowired
    private InventoryDao inventoryDao;

    @RequestMapping(value = "/addInventory", method = RequestMethod.GET)
    public String addInventory(Model model) {
        model.addAttribute("inventory", new Inventory());

        List categories = categoryDao.getListCategory();
        model.addAttribute("categories", categories);

        List instruments = instrumentDao.getListInstrument();
        model.addAttribute("instruments", instruments);

        List stores = storeDao.getListStore();
        model.addAttribute("stores", stores);

        List inventories = inventoryDao.getListInventory();
        model.addAttribute("inventories", inventories);
        return "addInventory";
    }

    @RequestMapping(value = "/addInventory", method = RequestMethod.POST)
    public ModelAndView addInventoryPost(@ModelAttribute("inventory") Inventory inventory, ModelAndView model) {
        inventory = inventoryDao.addInventory(inventory);

        List categories = categoryDao.getListCategory();
        model.addObject("categories", categories);

        List instruments = instrumentDao.getListInstrument();
        model.addObject("instruments", instruments);

        List inventories = inventoryDao.getListInventory();
        model.addObject("inventories", inventories);

        List stores = storeDao.getListStore();
        model.addObject("stores", stores);

        model.addObject("successMsh", "Inventory added!");
        model.addObject("inventory", new Inventory());
        return model;

    }

    @RequestMapping(value = "/deleteInventory/{id}", method = RequestMethod.GET)
    public String deleteInventory(@PathVariable("id") int id, HttpServletRequest request) {
        Inventory inventory = inventoryDao.getInventoryById(id);
        if (inventory == null) {
            String referer = request.getHeader("Referer");
            return "redirect:" + referer;
        }

        inventoryDao.deleteInventory(inventory);
        String referer = request.getHeader("Referer");
        return "redirect:" + referer;
    }

    @RequestMapping(value = "/editInventory/{id}", method = RequestMethod.GET)
    public String editInventory(@PathVariable("id") int id, Model model) {
        Inventory inventory = inventoryDao.getInventoryById(id);
        model.addAttribute("inventory", inventory);

        List categories = categoryDao.getListCategory();
        model.addAttribute("categories", categories);

        List instruments = instrumentDao.getListInstrument();
        model.addAttribute("instruments", instruments);

        List inventories = inventoryDao.getListInventory();
        model.addAttribute("inventories", inventories);

        List stores = storeDao.getListStore();
        model.addAttribute("stores", stores);

        return "addInventory";
    }
}
