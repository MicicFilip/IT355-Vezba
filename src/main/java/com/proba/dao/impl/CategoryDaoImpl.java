/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proba.dao.impl;

import com.proba.dao.CategoryDao;
import com.proba.entity.Category;
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
@Repository("categoryDao")
@Service
public class CategoryDaoImpl implements CategoryDao {

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
    public List<Category> getListCategory() {
        return getSession().createCriteria(Category.class).list();
    }

    @SuppressWarnings("unchecked")
    @Transactional
    @Override
    public Category addCategory(Category category) {
        return (Category) getSession().merge(category);
    }

    @SuppressWarnings("unchecked")
    @Transactional
    @Override
    public Category getCategoryById(Integer id) {
        return (Category) getSession().createCriteria(Category.class).add(Restrictions.eq("id", id)).uniqueResult();
    }

    @SuppressWarnings("unchecked")
    @Transactional
    @Override
    public boolean deleteCategory(Category category) {
        try {
            getSession().delete(category);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
