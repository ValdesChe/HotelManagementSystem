package com.alphahotel.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;

import java.io.Serializable;

/**
 * Created by ValdoR on 2019-12-11.
 *
 * Une classe Hibernate appellée SessionFactory permet à partir du fichier de configuration
 (hibernate.cfg.xml) d'être associé à la source de données. Elle fournit des objets Session pour
 manipuler les données.
 */

@SuppressWarnings("ALL")
public final class HibernateUtils implements Serializable {

    private Session session;
    private Transaction transaction;
    private static SessionFactory sessionFactory;
    private static String fileName = "hibernate.cfg.xml";
    static{
        try {
            AnnotationConfiguration acfg = new AnnotationConfiguration();
            acfg.configure();
            sessionFactory = acfg.buildSessionFactory();
        }
        catch (Exception ex) {
            System.out.println("An error occurred during the Session Factory creation : " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

}