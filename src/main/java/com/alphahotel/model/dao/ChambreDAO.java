package com.alphahotel.model.dao;

import com.alphahotel.model.entities.Chambre;
import org.hibernate.Query;

/**
 * Created by ValdoR on 2019-12-12.
 */
public class ChambreDAO extends HibernateDAO<Chambre>{
    public ChambreDAO() {
        super(Chambre.class);
    }

}