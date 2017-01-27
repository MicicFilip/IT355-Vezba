/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proba.dao;

import com.proba.entity.Instrument;
import java.util.List;

/**
 *
 * @author Filip
 */
public interface InstrumentDao {
    
    public List<Instrument> getListInstrument();
    
    public Instrument addInstrument(Instrument instrument);
    
    public Instrument getInstrumentById(Integer instrumentId);
    
    public boolean deleteInstrument(Instrument instrument);
    
    public List<Instrument> instrumentsByCategoryId(Integer id);
}
