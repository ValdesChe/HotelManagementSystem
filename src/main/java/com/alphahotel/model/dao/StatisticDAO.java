package com.alphahotel.model.dao;

import com.alphahotel.model.ItemStatistic;
import com.alphahotel.model.entities.Reservation;
import org.hibernate.Query;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ValdoR on 2019-12-20.
 */
public class StatisticDAO extends HibernateDAO<ItemStatistic>{
    public StatisticDAO() {
        super(ItemStatistic.class);
    }
    public List<ItemStatistic> parseResultSetStat(List listStats, String type) {
        List<ItemStatistic> itemStatisticList = new ArrayList<ItemStatistic>();

        for (Object object : listStats) {
            Object[] result = (Object[]) object;
            ItemStatistic itemStatistic = null;
            if(type.equals("LONG"))
                itemStatistic = new ItemStatistic((String) result[0], (Long) result[1]);
            else if(type.equals("DOUBLE")) {
                Long total = (new Double(result[1].toString())).longValue();
                itemStatistic = new ItemStatistic((String) result[0], total);
            }else
                itemStatistic = new ItemStatistic((String) result[0], (Long) result[1]);
            itemStatisticList.add(itemStatistic);
        }
        return itemStatisticList;
    }
    /**
     * Give a list of Statistic groupBy Month for a special year
     * @param year
     * @return
     */
    public List<ItemStatistic> getRevenueGroupByMonth(String year){
        // Query query1 = getSession().createSQLQuery("SELECT ");
        String query = "SELECT DATE_FORMAT(r.date_debut, '%M %Y') AS name, " +
                "SUM(r.total) AS mark " +
                "FROM Reservation r " +
                " WHERE DATE_FORMAT(r.date_debut, '%M %Y') LIKE :year " +
                "GROUP BY DATE_FORMAT(r.date_debut, '%M %Y') ";
        System.out.println(query);
        List<Object> listStats = getSession()
                .createQuery(query)
                .setParameter("year", "%" + year + "%")
                .list();
        return parseResultSetStat(listStats, "DOUBLE");

    }

    /**
     * Give a list of Statistic groupBy bedroom
     * @return
     */
    public List<ItemStatistic> getRevenueGroupByBedroom(){
        String query = "SELECT c.libele AS name, " +
                "COUNT(r.chambre_id) AS mark " +
                "FROM Reservation r, Chambre c " +
                " WHERE r.chambre_id = c.id " +
                " GROUP BY  c.libele " ;
        List<Object> listStats = getSession()
                .createQuery(query)
                .list();
        return parseResultSetStat(listStats, "LONG");
    }

    public List<ItemStatistic> getRevenueGroupByBedroomForYear(String year){
        String query = "SELECT c.libele AS name, " +
                "COUNT(r) AS mark " +
                "FROM Reservation r, Chambre c " +
                " WHERE r.chambre.id = c.id AND DATE_FORMAT(r.date_debut, '%M %Y') LIKE :year" +
                " GROUP BY  c.libele " ;
        List<Object> listStats = getSession()
                .createQuery(query)
                .setParameter("year", "%" + year + "%")
                .list();

        return parseResultSetStat(listStats, "LONG");
    }

    /**
     * Give the total of client for special year group by months
     * @param year
     * @return
     */
    public List<ItemStatistic> getTotalClientGroupByMonth(String year){
        String query = "SELECT DATE_FORMAT(r.date_debut, '%M %Y') AS month, " +
                "SUM(r.total) AS mark" +
                "FROM Reservation r " +
                " WHERE DATE_FORMAT(r.date_debut, '%M %Y') LIKE :year " +
                "GROUP BY DATE_FORMAT(r.date_debut, '%M %Y') ";
        List<Object> listStats = getSession()
                .createQuery(query)
                .setParameter("year", "%" + year + "%")
                .list();

        return parseResultSetStat(listStats, "DOUBLE");
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

        return parseResultSetStat(listStats, "LONG");
    }
}