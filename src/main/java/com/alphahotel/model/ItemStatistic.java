package com.alphahotel.model;

/**
 * Created by ValdoR on 2019-12-24.
 */
public class ItemStatistic {
    private String name;
    private Long mark;

    public ItemStatistic() {
    }

    public ItemStatistic(String name, Long mark) {
        this.name = name;
        this.mark = mark;
    }
    public ItemStatistic(String name, Double mark) {
        this.name = name;
        this.mark = Double.doubleToLongBits(mark);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getMark() {
        return mark;
    }

    public void setMark(Long mark) {
        this.mark = mark;
    }
}