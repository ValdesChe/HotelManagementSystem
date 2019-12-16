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
        this.session = HibernateUtils.getSessionFactory().openSession();
        transaction = session.beginTransaction();
    }

    @Override
    public void save(T entity) {
        session.save(entity);
    }

    @Override
    public void update(T entity) {
        session.update(entity);
    }

    @Override
    public void remove(T entity) {
        session.delete(entity);
    }

    @Override
    public void merge(T entity) {
        session.merge(entity);
    }

    @Override
    public T getEntity(Serializable id) {
        T entity = (T)session.get(classe, id);
        return entity;
    }

    @Override
    public T getEntityByDetachedCriteria(DetachedCriteria criteria) {
        T entity = (T)criteria.getExecutableCriteria(session).uniqueResult();
        return entity;
    }


    @Override
    public T getEntityByHQLQuery(String stringQuery) {
        Query query = session.createQuery(stringQuery);
        return (T) query.uniqueResult();
    }

    @Override
    public List<T> getListByDetachedCriteria(DetachedCriteria criteria) {
        return criteria.getExecutableCriteria(session).list();
    }

    @Override
    public List<T> getEntities() {
        List<T> enties = (List<T>) session.createCriteria(classe).list();
        return enties;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

}
