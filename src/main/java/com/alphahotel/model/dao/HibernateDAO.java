package com.alphahotel.model.dao;

import java.io.Serializable;
import java.util.List;

import com.alphahotel.utils.HibernateUtils;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
/**
 * Created by ValdoR on 2019-12-11.
 */
public class HibernateDAO<T> implements InterfaceDAO<T>, Serializable {
    private static final long serialVersionUID = 1L;

    private Class<T> classe;
    private Session session = null;
    private Transaction transaction = null;

    public HibernateDAO(Class<T> classe) {
        super();
        this.classe = classe;

    }

    @Override
    public void save(T entity) {
        this.session = HibernateUtils.getSessionFactory().openSession();
        this.transaction = session.beginTransaction();
        session.save(entity);
        this.transaction.commit();
        this.session.close();

    }

    @Override
    public void update(T entity) {
        this.session = HibernateUtils.getSessionFactory().openSession();
        this.transaction = session.beginTransaction();
        session.update(entity);
        this.transaction.commit();
        this.session.close();
    }

    @Override
    public void remove(T entity) {
        this.session = HibernateUtils.getSessionFactory().openSession();
        this.transaction = session.beginTransaction();
        session.delete(entity);
        this.transaction.commit();
        this.session.close();
    }

    @Override
    public void merge(T entity) {
        this.session = HibernateUtils.getSessionFactory().openSession();
        this.transaction = session.beginTransaction();
        session.merge(entity);
        this.transaction.commit();
        this.session.close();
    }

    @Override
    public T getEntity(Serializable id) {
        this.session = HibernateUtils.getSessionFactory().openSession();
        this.transaction = session.beginTransaction();
        T entity = (T)session.get(classe, id);
        this.transaction.commit();
        this.session.close();
        return entity;
    }

    @Override
    public T getEntityByDetachedCriteria(DetachedCriteria criteria) {
        this.session = HibernateUtils.getSessionFactory().openSession();
        this.transaction = session.beginTransaction();
        T entity = (T)criteria.getExecutableCriteria(session).uniqueResult();
        this.transaction.commit();
        this.session.close();
        return entity;
    }


    @Override
    public T getEntityByHQLQuery(String stringQuery) {

        this.session = HibernateUtils.getSessionFactory().openSession();
        this.transaction = session.beginTransaction();

        Query query = session.createQuery(stringQuery);
        this.transaction.commit();
        this.session.close();
        return (T) query.uniqueResult();
    }

    @Override
    public List<T> getListByDetachedCriteria(DetachedCriteria criteria) {
        this.session = HibernateUtils.getSessionFactory().openSession();
        this.transaction = session.beginTransaction();
        List<T> entities = criteria.getExecutableCriteria(session).list();
        this.transaction.commit();
        this.session.close();
        return entities;
    }

    @Override
    public List<T> getEntities() {
        this.session = HibernateUtils.getSessionFactory().openSession();
        this.transaction = session.beginTransaction();
        List<T> enties = (List<T>) session.createCriteria(classe).list();
        this.transaction.commit();
        this.session.close();
        return enties;
    }

    public Session getSession() {
        if(session == null) {
            session = HibernateUtils.getSessionFactory().openSession();
        }
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

}
