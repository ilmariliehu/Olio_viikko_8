package com.example.week8_5;

public class Bottle {


    private  String name;
    private  String manufacturer;
    private  double total_energy;
    private  String size;
    private  double cost;



    public Bottle(String n, String m, double t, String s, double c){
        name = n;
        manufacturer = m;
        total_energy = t;
        size = s;
        cost = c;
    }


    public String getName(){
        return name;
    }


    public String getSize() {
        return size;
    }

    public double getCost() {
        return cost;
    }

}
