package com.alphahotel.utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

/**
 * Created by ValdoR on 2019-12-11.
 *
 * Une classe Hibernate appellée SessionFactory permet à partir du fichier de configuration
 (hibernate.cfg.xml) d'être associé à la source de données. Elle fournit des objets Session pour
 manipuler les données.
 */

public class HibernateUtils {

    private static final SessionFactory sessionFactory;
    public static final String HIBERNATE_SESSION = "HIBERNATE_SESSION";

    static{

        try {

            System.out.println("Attempting to configure a session factory (SF) !");

            Configuration configuration = new Configuration().configure();

            ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().
                    applySettings(configuration.getProperties()).buildServiceRegistry();

            sessionFactory = configuration.buildSessionFactory(serviceRegistry);

            System.out.println("Session Factory created correctly !");
        } catch (Exception ex) {
            System.out.println("An error occurred during the Session Factory creation : " + ex);
            throw new ExceptionInInitializerError(ex);
        }


    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

}