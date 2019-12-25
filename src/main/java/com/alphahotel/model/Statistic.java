package com.alphahotel.model;

import java.util.List;

public class Statistic {
    private String graphDescription;
    private List<ItemStatistic> listOfRevenues;

    public Statistic() {
    }

    public Statistic(String graphDescription, List<ItemStatistic> listOfRevenues) {
        this.graphDescription = graphDescription;
        this.listOfRevenues = listOfRevenues;
    }

    public String getGraphDescription() {
        return graphDescription;
    }

    public void setGraphDescription(String graphDescription) {
        this.graphDescription = graphDescription;
    }

    public List<ItemStatistic> getListOfRevenues() {
        return listOfRevenues;
    }

    public void setListOfRevenues(List<ItemStatistic> listOfRevenues) {
        this.listOfRevenues = listOfRevenues;
    }
}