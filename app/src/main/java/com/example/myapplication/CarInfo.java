package com.example.myapplication;

public class CarInfo {
    private int CarNum;
    private String OwnerName;
    private  String Brand;
    private  String Model;
    private int Year;

    public CarInfo(int carNum, String ownerName, String brand, String model, int year) {
        CarNum = carNum;
        OwnerName = ownerName;
        Brand = brand;
        Model = model;
        Year = year;
    }

    @Override
    public String toString() {
        return "CarInfo{" +
                "CarNum=" + CarNum +
                ", OwnerName='" + OwnerName + '\'' +
                ", Brand='" + Brand + '\'' +
                ", Model='" + Model + '\'' +
                ", Year=" + Year +
                '}';
    }
}
