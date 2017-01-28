/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proba.dao.impl;

import com.proba.dao.InventoryDao;
import com.proba.entity.Instrument;
import com.proba.entity.Inventory;
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
@Repository("inventoryDao")
@Service
public class InventoryDaoImpl implements InventoryDao {

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
    public List<Inventory> getListInventory() {
        return getSession().createCriteria(Inventory.class).list();
    }

    @SuppressWarnings("unchecked")
    @Transactional
    @Override
    public Inventory addInventory(Inventory inventory) {
        return (Inventory) getSession().merge(inventory);
    }
    
    @SuppressWarnings("unchecked")
    @Transactional
    @Override
    public void editInventory(Inventory inventory) {
        getSession().saveOrUpdate(inventory);
    }

    @SuppressWarnings("unchecked")
    @Transactional
    @Override
    public Inventory getInventoryById(Integer id) {
        return (Inventory) getSession().createCriteria(Inventory.class).add(Restrictions.eq("id", id)).uniqueResult();
    }

    @SuppressWarnings("unchecked")
    @Transactional
    @Override
    public boolean deleteInventory(Inventory inventory) {
        try {
            getSession().delete(inventory);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
