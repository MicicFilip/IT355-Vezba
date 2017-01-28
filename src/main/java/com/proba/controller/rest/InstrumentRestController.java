/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proba.controller.rest;

import com.proba.dao.InstrumentDao;
import com.proba.entity.Instrument;
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
public class InstrumentRestController {

    @Autowired
    private InstrumentDao instrumentDao;

    @RequestMapping(value = "/instruments", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Instrument>> getInstruments() {
        System.out.println("Fetching instruments");

        List<Instrument> instruments = instrumentDao.getListInstrument();
        if (instruments.size() == 0) {
            System.out.println("Instrumenst list is empty");
            return new ResponseEntity<List<Instrument>>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<List<Instrument>>(instruments, HttpStatus.OK);
    }

    @RequestMapping(value = "/instrument/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Instrument> getInstrumentById(@PathVariable("id") int id) {
        System.out.println("Fetching instrument with id " + id);

        Instrument instrument = instrumentDao.getInstrumentById(id);
        if (instrument == null) {
            System.out.println("Instrument with id " + id + " not found");
            return new ResponseEntity<Instrument>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Instrument>(instrument, HttpStatus.OK);
    }

    @RequestMapping(value = "/instrument/", method = RequestMethod.POST)
    public ResponseEntity<Void> addInstrument(@RequestBody Instrument instrument) {
        System.out.println("Adding instrument " + instrument.toString());
        instrumentDao.addInstrument(instrument);

        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/instrument/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Instrument> editInstrument(@PathVariable("id") int id, @RequestBody Instrument instrument) {
        System.out.println("Updating instrument " + id);

        instrument.setId(id);
        instrumentDao.editInstrument(instrument);
        return new ResponseEntity<Instrument>(instrument, HttpStatus.OK);
    }

    @RequestMapping(value = "/instrument/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Instrument> deleteInstrument(@PathVariable("id") int id) {
        System.out.println("Fetching & Deleting instrument with id " + id);

        Instrument instrument = instrumentDao.getInstrumentById(id);
        if (instrument == null) {
            System.out.println("Unable to delete. Insturment with id:" + id + "not found");
            return new ResponseEntity<Instrument>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Instrument>(HttpStatus.OK);
    }
}
