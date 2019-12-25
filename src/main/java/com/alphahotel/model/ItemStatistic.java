package com.alphahotel.model;

/**
 * Created by ValdoR on 2019-12-24.
 */
public class ItemStatistic {
    private String name;
    private double mark;

    public ItemStatistic() {
    }

    public ItemStatistic(String name, double mark) {
        this.name = name;
        this.mark = mark;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getMark() {
        return mark;
    }

    public void setMark(double mark) {
        this.mark = mark;
    }
}