package com.alphahotel.controller;

import com.alphahotel.model.ItemStatistic;
import com.alphahotel.model.dao.StatisticDAO;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ValdoR on 2019-12-12.
 */
@ManagedBean(name = "statisticController")
@RequestScoped
public class StatisticController extends AbstractController  implements Serializable {
    private static final long serialVersionUID = -1;

    private JRBeanCollectionDataSource beanCollectionDataSource;
    private List<ItemStatistic> itemStatisticList;
    private StatisticDAO statisticDAO;

    private List<ItemStatistic> yearsList;

    public StatisticController() {
        statisticDAO = new StatisticDAO();
        yearsList = new ArrayList<>();
    }

    public void printFinanceStat(String year){

    }


    public JRBeanCollectionDataSource getBeanCollectionDataSource() {
        return beanCollectionDataSource;
    }

    public void setBeanCollectionDataSource(JRBeanCollectionDataSource beanCollectionDataSource) {
        this.beanCollectionDataSource = beanCollectionDataSource;
    }

    public List<ItemStatistic> getItemStatisticList() {
        return itemStatisticList;
    }

    public void setItemStatisticList(List<ItemStatistic> itemStatisticList) {
        this.itemStatisticList = itemStatisticList;
    }

    public List<ItemStatistic> getYearsList() {
        return statisticDAO.getEllapsedYears();
    }

    public void setYearsList(List<ItemStatistic> yearsList) {
        this.yearsList = yearsList;
    }
}
