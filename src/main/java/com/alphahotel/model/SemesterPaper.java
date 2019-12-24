package com.alphahotel.model;

/**
 * Created by ValdoR on 2019-12-24.
 */public class SemesterPaper {

    private String name;
    private double mark;

    public SemesterPaper() {
    }


    public SemesterPaper(String name, double mark) {
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