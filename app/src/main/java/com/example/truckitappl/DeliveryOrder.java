package com.example.truckitappl;

public class DeliveryOrder {
    private String receiverName;
    private int day;
    private int month;
    private int year;
    private String location;
    private String goodsType;
    private String vehicleType;

    public DeliveryOrder(String receiverName, int day, int month, int year, String location, String goodsType, String vehicleType) {
        this.receiverName = receiverName;
        this.day = day;
        this.month = month;
        this.year = year;
        this.location = location;
        this.goodsType = goodsType;
        this.vehicleType = vehicleType;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(String goodsType) {
        this.goodsType = goodsType;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }
}

