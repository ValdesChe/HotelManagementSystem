package com.alphahotel.model;

import java.util.List;

public class Statistic {
    private String graphName;
    private List<ItemStatistic> itemStatisticList;

    public Statistic() {
    }

    public Statistic(String graphName, List<ItemStatistic> itemStatisticList) {
        this.graphName = graphName;
        this.itemStatisticList = itemStatisticList;
    }

    public String getGraphName() {
        return graphName;
    }

    public void setGraphName(String graphName) {
        this.graphName = graphName;
    }

    public List<ItemStatistic> getItemStatisticList() {
        return itemStatisticList;
    }

    public void setItemStatisticList(List<ItemStatistic> itemStatisticList) {
        this.itemStatisticList = itemStatisticList;
    }
}