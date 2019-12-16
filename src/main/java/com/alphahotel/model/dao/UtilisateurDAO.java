package com.alphahotel.model.dao;

import com.alphahotel.model.entities.Utilisateur;
import com.alphahotel.utils.HibernateUtils;
import org.hibernate.Query;

/**
 * Created by ValdoR on 2019-12-12.
 */
public class UtilisateurDAO extends HibernateDAO<Utilisateur>{

    public UtilisateurDAO() {
        super(Utilisateur.class);

    }

    public Utilisateur findByLogin(String login){
        return (Utilisateur) getSession().getNamedQuery("Utilisateur.findByLogin").setParameter("login", login).uniqueResult();

    }

    public Utilisateur findByEmail(String email){
        Query query = getSession().getNamedQuery("Utilisateur.findByEmail").setParameter("email", email);
        return getEntityByHQLQuery(query.getQueryString());
        // return createNamedQuery("Utilisateur.findByEmail").setParameter("email", email).getSingleResult();
    }
}