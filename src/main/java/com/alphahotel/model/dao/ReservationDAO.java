package com.alphahotel.model.dao;

import com.alphahotel.model.entities.Reservation;

/**
 * Created by ValdoR on 2019-12-20.
 */
public class ReservationDAO extends HibernateDAO<Reservation>{
    public ReservationDAO() {
        super(Reservation.class);
    }
}