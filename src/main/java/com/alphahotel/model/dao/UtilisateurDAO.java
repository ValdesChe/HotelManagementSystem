package com.alphahotel.model.dao;

import com.alphahotel.model.entities.Utilisateur;
import com.alphahotel.utils.HibernateUtils;
import org.hibernate.Query;

import java.util.List;

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
        return (Utilisateur) getSession().getNamedQuery("Utilisateur.findByEmail").setParameter("email", email).uniqueResult();
    }


    // Total des utilisateurs par role
    public Long getCountUtilisateurByRole(String role){
        Long count = (Long) getSession()
                .getNamedQuery("Utilisateur.countByRole")
                .setParameter("role", role)
                .uniqueResult();
        if(count == null)
            return Long.getLong("0");
        return  count;
    }
}