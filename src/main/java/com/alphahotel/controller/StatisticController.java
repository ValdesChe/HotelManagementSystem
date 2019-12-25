package com.alphahotel.controller;

import com.alphahotel.model.dao.ReservationDAO;
import com.alphahotel.model.entities.Reservation;
import com.alphahotel.model.entities.ReservationStatus;
import com.alphahotel.utils.FilePrinterUtil;
import com.alphahotel.utils.Helpers;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.io.Serializable;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by ValdoR on 2019-12-12.
 */
@ManagedBean(name = "statisticController")
@RequestScoped
public class StatisticController extends AbstractController  implements Serializable {
    private static final long serialVersionUID = -1;

}
