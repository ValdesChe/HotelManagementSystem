package com.alphahotel.model.dao;

import com.alphahotel.model.ItemStatistic;
import com.alphahotel.model.entities.Reservation;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ValdoR on 2019-12-20.
 */
public class StatisticDAO extends HibernateDAO<ItemStatistic>{
    public StatisticDAO() {
        super(ItemStatistic.class);
    }

    /**
     * Give a list of Statistic groupBy Month for a special year
     * @param year
     * @return
     */
    public List<ItemStatistic> getRevenueGroupByMonth(String year){
        String query = "SELECT DATE_FORMAT(r.date_debut, '%M %Y') AS name, " +
                "SUM(r.total) AS mark, DATE_FORMAT(r.date_debut, '%Y')  " +
                "FROM Reservation r " +
                "GROUP BY DATE_FORMAT(r.date_debut, '%M %Y') " +
                " WHERE DATE_FORMAT(r.date_debut, '%Y') LIKE '%".concat(year).concat("'");
        List<Object> listStats = getSession()
                .createQuery(query)
                .list();
        List<ItemStatistic> itemStatisticList = new ArrayList<ItemStatistic>();

        for (Object object : listStats) {
            Object[] result = (Object[]) object;
            ItemStatistic itemStatistic = new ItemStatistic((String) result[0], (Double) result[1] );
            itemStatisticList.add(itemStatistic);
        }
        return itemStatisticList;
    }

    /**
     * Give a list of Statistic groupBy bedroom
     * @return
     */
    public List<ItemStatistic> getRevenueGroupByBedroom(){
        String query = "SELECT c.libele AS name, " +
                "COUNT(r.chambre_id) AS mark " +
                "FROM Reservation r, Chambre c " +
                "GROUP BY name " +
                " WHERE r.chambre_id = chambre.id ";
        List<Object> listStats = getSession()
                .createQuery(query)
                .list();
        List<ItemStatistic> itemStatisticList = new ArrayList<ItemStatistic>();

        for (Object object : listStats) {
            Object[] result = (Object[]) object;
            ItemStatistic itemStatistic = new ItemStatistic((String) result[0], (Double) result[1] );
            itemStatisticList.add(itemStatistic);
        }
        return itemStatisticList;
    }

    /**
     * Give the total of client for special year group by months
     * @param year
     * @return
     */
    public List<ItemStatistic> getTotalClientGroupByMonth(String year){
        String query = "SELECT DATE_FORMAT(r.date_debut, '%M %Y') AS month, " +
                "SUM(r.total) AS mark, DATE_FORMAT(r.date_debut, '%Y')  " +
                "FROM Reservation r " +
                "GROUP BY DATE_FORMAT(r.date_debut, '%M %Y') " +
                " WHERE DATE_FORMAT(r.date_debut, '%Y') = :year";
        List<Object> listStats = getSession()
                .createQuery(query)
                .setParameter("year", year)
                .list();
        List<ItemStatistic> itemStatisticList = new ArrayList<ItemStatistic>();

        for (Object object : listStats) {
            Object[] result = (Object[]) object;
            ItemStatistic itemStatistic = new ItemStatistic((String) result[0], (Double) result[1] );
            itemStatisticList.add(itemStatistic);
        }
        return itemStatisticList;
    }

    /**
     * Liste des années d'Activité de l'hotel et le nombre de reservations
     * @return
     */
    public List<ItemStatistic> getElapsedYears(){
        String query = "SELECT  DATE_FORMAT(r.date_debut, '%Y') AS name," +
                " COUNT(*) AS mark " +
                "FROM Reservation r " +
                "GROUP BY DATE_FORMAT(r.date_debut, '%Y') "
                        .concat("ORDER BY DATE_FORMAT(r.date_debut, '%Y') ASC");
        List<Object> listStats = getSession()
                .createQuery(query)
                .list();
        List<ItemStatistic> itemStatisticList = new ArrayList<ItemStatistic>();

        for (Object object : listStats) {
            Object[] result = (Object[]) object;
            ItemStatistic itemStatistic = new ItemStatistic();
            itemStatistic.setName((String) result[0]);
            itemStatistic.setMark((Long) result[1]);
            itemStatisticList.add(itemStatistic);
        }
        return itemStatisticList;
    }


}