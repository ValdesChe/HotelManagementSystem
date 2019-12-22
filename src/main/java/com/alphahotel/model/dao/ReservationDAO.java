package com.alphahotel.model.dao;

import com.alphahotel.model.entities.Reservation;
import org.hibernate.*;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Property;

import java.util.List;

/**
 * Created by ValdoR on 2019-12-20.
 */
public class ReservationDAO extends HibernateDAO<Reservation>{
    public ReservationDAO() {
        super(Reservation.class);
    }

    public List<Reservation> findByStatus(String status){
        return getSession()
                .getNamedQuery("Reservation.findByStatus")
                .setParameter("status_reservation", status)
                .list();
    }


}