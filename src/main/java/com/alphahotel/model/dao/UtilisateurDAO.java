package com.alphahotel.model.dao;

import com.alphahotel.model.entities.Utilisateur;

/**
 * Created by ValdoR on 2019-12-12.
 */
public class UtilisateurDAO extends GenericDAO<Utilisateur>{

    public UtilisateurDAO() {
        super(Utilisateur.class);
    }

    public Utilisateur findByLogin(String login){
        return createNamedQuery("Utilisateur.findByLogin").setParameter("login", login).getSingleResult();
    }

    public Utilisateur findByEmail(String email){
        return createNamedQuery("Utilisateur.findByEmail").setParameter("email", email).getSingleResult();
    }
}