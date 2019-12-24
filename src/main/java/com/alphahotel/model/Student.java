package com.alphahotel.model;

import com.alphahotel.model.SemesterPaper;

import java.util.List;

public class Student {
    private String name;
    private String rollNo;
    private String imagePath;
    private List<SemesterPaper> listOfSemesterPaper;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRollNo() {
        return rollNo;
    }

    public void setRollNo(String rollNo) {
        this.rollNo = rollNo;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public List<SemesterPaper> getListOfSemesterPaper() {
        return listOfSemesterPaper;
    }

    public void setListOfSemesterPaper(List<SemesterPaper> listOfSemesterPaper) {
        this.listOfSemesterPaper = listOfSemesterPaper;
    }



}