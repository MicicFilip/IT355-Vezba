/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proba.dao.impl;

import com.proba.dao.StoreDao;
import com.proba.entity.Instrument;
import com.proba.entity.Store;
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
 * @author Micic
 */
@Repository("storeDao")
@Service
public class StoreDaoImpl implements StoreDao {

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
    public List<Store> getListStore() {
        return getSession().createCriteria(Store.class).list();
    }

    @SuppressWarnings("unchecked")
    @Transactional
    @Override
    public Store addStore(Store store) {
        return (Store) getSession().merge(store);
    }

    @SuppressWarnings("unchecked")
    @Transactional
    @Override
    public void editStore(Store store) {
        getSession().saveOrUpdate(store);
    }

    @SuppressWarnings("unchecked")
    @Transactional
    @Override
    public Store getStoreById(Integer id) {
        return (Store) getSession().createCriteria(Store.class).add(Restrictions.eq("id", id)).uniqueResult();
    }

    @SuppressWarnings("unchecked")
    @Transactional
    @Override
    public boolean deleteStore(Store store) {
        try {
            getSession().delete(store);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
