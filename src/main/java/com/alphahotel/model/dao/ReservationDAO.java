package com.alphahotel.model.dao;

import com.alphahotel.model.ItemStatistic;
import com.alphahotel.model.entities.Reservation;
import org.hibernate.*;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Property;

import java.util.ArrayList;
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

    // Number of reservation findByStatus
    public Long getCountReservationByStatus(String status){
        Long count =  (Long) getSession()
                .getNamedQuery("Reservation.total.findByStatus")
                .setParameter("status_reservation", status)
                .uniqueResult();
        if(count == null)
            return Long.getLong("0");
        return  count;
    }

    // Total revenues of reservation findByStatus
    public Double getTotalRevenueByStatus(String status){
        Double count =  (Double) getSession()
                .getNamedQuery("Reservation.totalRevenues.findByStatus")
                .setParameter("status_reservation", status)
                .uniqueResult();
        if(count == null)
            return 0.0;
        return  count;
    }

    // Total revenues of reservation by year and status
    public Double getTotalRevenueByStatus(String year, String status){
        Double count = (Double) getSession()
                .getNamedQuery("Reservation.totalRevenues.findByStatusYear")
                .setParameter("status_reservation", status)
                .setParameter("year", year)
                .uniqueResult();
        if(count == null)
            return 0.0;
        return  count;
    }
}