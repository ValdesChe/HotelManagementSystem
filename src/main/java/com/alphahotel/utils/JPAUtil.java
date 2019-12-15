package com.alphahotel.utils;

/**
 * Created by ValdoR on 2019-12-12.
 */

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.transaction.Transactional;

@Transactional
public class JPAUtil {
    private static final EntityManagerFactory emf;
    private static EntityManager entityManager = null;

    static {
        emf = Persistence.createEntityManagerFactory("ALPHA_HOTEL_PU");
    }

    public static EntityManager getEntityManager(){
        if(entityManager==null){
            entityManager = emf.createEntityManager();
        }
        return entityManager;
    }
}
