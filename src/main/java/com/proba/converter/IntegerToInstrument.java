/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proba.converter;


import com.proba.dao.InstrumentDao;
import com.proba.entity.Instrument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 *
 * @author Filip
 */
@Component
public class IntegerToInstrument implements Converter<String, Instrument> {

    @Autowired
    InstrumentDao instrumentDao;
    
    @Override
    public Instrument convert(String s) {
        if(s.isEmpty()){
            return null;
        }
        Integer valeu = Integer.valueOf(s);
        Instrument cat = instrumentDao.getInstrumentById(valeu);
        return cat;
    }

}
