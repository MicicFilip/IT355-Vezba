/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proba.dao.impl;

import com.proba.dao.InstrumentDao;
import com.proba.entity.Category;
import com.proba.entity.Instrument;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Filip
 */
@Repository("instrumentDao")
@Service
public class InstrumentDaoImpl implements InstrumentDao {

    @SuppressWarnings("unused")
    private final Log logger = LogFactory.getLog(getClass());

    //Instanciramo sesiju
    @Autowired
    private SessionFactory sessionFactory;

    //kreiramo seter za sesiju
    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    //kreiramo geter za sesiju
    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @SuppressWarnings("unchecked")
    @Transactional
    @Override
    public List<Instrument> getListInstrument() {
        return getSession().createCriteria(Instrument.class).list();
    }

    @SuppressWarnings("unchecked")
    @Transactional
    @Override
    public Instrument addInstrument(Instrument instrument) {
        return (Instrument) getSession().merge(instrument);
    }

    @SuppressWarnings("unchecked")
    @Transactional
    @Override
    public Instrument getInstrumentById(Integer id) {
        return (Instrument) getSession().createCriteria(Instrument.class).add(Restrictions.eq("id", id)).uniqueResult();
    }

    @SuppressWarnings("unchecked")
    @Transactional
    @Override
    public boolean deleteInstrument(Instrument instrument) {
        try {
            getSession().delete(instrument);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @SuppressWarnings("unchecked")
    @Transactional
    @Override
    public List<Instrument> instrumentsByCategoryId(Integer id) {
        try {
            Category cat = (Category) getSession().createCriteria(Category.class).add(Restrictions.eq("id", id)).uniqueResult();
            List inst = getSession().createCriteria(Instrument.class).add(Restrictions.eq("categoryId", cat)).list();
            return inst;
        } catch (Exception ex) {
            return null;
        }
    }

}
