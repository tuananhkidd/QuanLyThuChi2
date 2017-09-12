package com.example.lavotinh.quanlythuchi.model;

/**
 * Created by La Vo Tinh on 12/09/2017.
 */

public class work {
    private int ID;
    private String name;
    private int sumOfMoney;
    private String date;
    private String time;

    public work(int ID, String name, int sumOfMoney, String date, String time) {
        this.ID = ID;
        this.name = name;
        this.sumOfMoney = sumOfMoney;
        this.date = date;
        this.time = time;
    }

    public work() {
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSumOfMoney() {
        return sumOfMoney;
    }

    public void setSumOfMoney(int sumOfMoney) {
        this.sumOfMoney = sumOfMoney;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
