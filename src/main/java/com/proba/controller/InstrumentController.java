/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proba.controller;

import com.proba.dao.CategoryDao;
import com.proba.dao.InstrumentDao;
import com.proba.entity.Instrument;
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
 * @author Filip
 */
@Controller
public class InstrumentController {

    @Autowired
    private CategoryDao categoryDao;

    @Autowired
    private InstrumentDao instrumentDao;

    @RequestMapping(value = "/addInstrument", method = RequestMethod.GET)
    public String addInstrument(Model model) {
        model.addAttribute("instrument", new Instrument());
        model.addAttribute("instruments", instrumentDao.getListInstrument());
        model.addAttribute("categories", categoryDao.getListCategory());
        return "addInstrument";
    }

    @RequestMapping(value = "/addInstrument", method = RequestMethod.POST)
    public ModelAndView addInstrumentPost(@ModelAttribute("instrument") Instrument instrument, ModelAndView model) {
        instrument = instrumentDao.addInstrument(instrument);
        model.addObject("categories", categoryDao.getListCategory());
        model.addObject("successMsg", "Instrument added");
        model.addObject("instruments", instrumentDao.getListInstrument());
        model.addObject("instrument", new Instrument());
        return model;
    }

    @RequestMapping(value = "/deleteInstrument/{id}", method = RequestMethod.GET)
    public String deleteInstrument(@PathVariable("id") int id, HttpServletRequest request) {
        Instrument instrument = instrumentDao.getInstrumentById(id);
        if (instrument == null) {
            String referer = request.getHeader("Referer");
            return "redirect:" + referer;
        }
        instrumentDao.deleteInstrument(instrument);
        String referer = request.getHeader("Referer");
        return "redirect:" + referer;

    }
    
    @RequestMapping(value = "/editInstrument/{id}", method = RequestMethod.GET)
    public String editInstrument(@PathVariable("id") int id, Model model){
        Instrument instrument = instrumentDao.getInstrumentById(id);
        model.addAttribute("instrument", instrument);
        model.addAttribute("categories", categoryDao.getListCategory());
        List inst = instrumentDao.getListInstrument();
        model.addAttribute("instruments", inst);
        return "addInstrument";
    }
}
