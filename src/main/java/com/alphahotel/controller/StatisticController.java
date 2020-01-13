package com.alphahotel.controller;

import com.alphahotel.model.ItemStatistic;
import com.alphahotel.model.Statistic;
import com.alphahotel.model.dao.StatisticDAO;
import com.alphahotel.model.entities.ReservationStatus;
import com.alphahotel.utils.FilePrinterUtil;
import com.alphahotel.utils.Helpers;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
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

    private HashMap<String, Object> parameters;
    public StatisticController() {
        statisticDAO = new StatisticDAO();
        yearsList = new ArrayList<>();
    }

    public void printFinanceStatPdf(String year){
        String graphName = "Finances des reservations de l'année ".concat(year);
        List<ItemStatistic> itemStatisticList = fetchItemStatisticsList(year);
        setItemStatisticList(itemStatisticList);
        Statistic statistic = new Statistic(graphName, itemStatisticList);
        ArrayList<Statistic> statistics = new ArrayList<Statistic>();
        statistics.add(statistic);
        beanCollectionDataSource = new JRBeanCollectionDataSource(statistics);
        parameters = new HashMap<>();
        parameters.put("IMAGE_PATH",
                FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/images"));

        try {
            FilePrinterUtil.generateFile("PDF",
                    "/reports/finance_statistics.jasper",
                    graphName.concat(".pdf"),
                    parameters,
                    beanCollectionDataSource
            );
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JRException e) {
            e.printStackTrace();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void showFinanceStatPdf(String year){
        String graphName = "Finances des reservations de l'année ".concat(year);
        List<ItemStatistic> itemStatisticList = fetchItemStatisticsList(year);
        setItemStatisticList(itemStatisticList);
        Statistic statistic = new Statistic(graphName, itemStatisticList);
        ArrayList<Statistic> statistics = new ArrayList<Statistic>();
        statistics.add(statistic);
        beanCollectionDataSource = new JRBeanCollectionDataSource(statistics);
        parameters = new HashMap<>();
        parameters.put("IMAGE_PATH",
                FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/images"));

        try {
            FilePrinterUtil.generateFile("LOAD_PDF",
                    "/reports/finance_statistics.jasper",
                    graphName.concat(".pdf"),
                    parameters,
                    beanCollectionDataSource
            );
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JRException e) {
            e.printStackTrace();
        }
        catch (Exception e){
            e.printStackTrace();
        }
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

    public List<ItemStatistic> fetchItemStatisticsList(String year) {
        return statisticDAO.getRevenueGroupByMonth(year);
    }


    public void setItemStatisticList(List<ItemStatistic> itemStatisticList) {
        this.itemStatisticList = itemStatisticList;
    }

    public List<ItemStatistic> getYearsList() {
        return statisticDAO.getElapsedYears();
    }

    public void setYearsList(List<ItemStatistic> yearsList) {
        this.yearsList = yearsList;
    }

}
