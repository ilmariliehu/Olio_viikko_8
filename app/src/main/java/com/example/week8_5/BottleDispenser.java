package com.example.week8_5;



import android.content.Context;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;



public class BottleDispenser {


    private static BottleDispenser BD = new BottleDispenser();


    ArrayList<Bottle> bottle_list = new ArrayList<Bottle>();

    int bottles;
    double money;
    double cost;
    int i;
    int a;
    //Context context = null;


    private BottleDispenser() {

        bottles = 5;
        money = 0.0;

        // Add Bottle-objects to the array

        String name = "n", manuf = "m", Size = "k";
        double totE = 0, Cost = 0;

        for (i = 0; i < bottles; i++) {

            if (i == 0) {
                name = "Pepsi Max";
                manuf = "Pepsi";
                totE = 0.3;
                Size = "0.50";
                Cost = 1.8;
            }
            if (i == 1) {
                name = "Pepsi Max";
                manuf = "Pepsi";
                totE = 0.3;
                Size = "1.50";
                Cost = 2.2;
            }
            if (i == 2) {
                name = "Coca-Cola Zero";
                manuf = "Coca-cola";
                totE = 0.3;
                Size = "0.50";
                Cost = 2.0;
            }
            if (i == 3) {
                name = "Coca-Cola Zero";
                manuf = "Coca-cola";
                totE = 0.3;
                Size = "1.50";
                Cost = 2.5;
            }
            if (i == 4) {
                name = "Fanta Zero";
                manuf = "Coca-cola";
                totE = 0.3;
                Size = "0.50";
                Cost = 1.95;
            }


            bottle_list.add(i, new Bottle(name, manuf, totE, Size, Cost));
        }
    }

    public static BottleDispenser getInstance() {
        return BD;
    }


    public String addMoney(double value) {

        money += value;

        String add = ("Klink! Added more money! " + value + "€");
        return add;

    }

    public int buyBottle(String name, String size) {

        for (a = 0; a <= bottles-1; a++){
            if(name.equals(bottle_list.get(a).getName()) && size.equals(bottle_list.get(a).getSize())) {

                cost =  bottle_list.get(a).getCost();

                if (money == 0.0 || money < cost) {
                    return -1;
                } else {
                    bottles -= 1;
                    money -= cost;
                    bottle_list.remove(a);
                    return 0;
                }
            }else{
                continue;
                }
        }
        return -2;
    }



    public void receipt(String name, String size, Context context){
        String t = ("*****KUITTI*****\n" +
                "Ostokset:\n" +
                "-" + name + "; " + size+ "; "+cost+"€");
         System.out.println(t);

        try{
            OutputStreamWriter ows = new OutputStreamWriter(context.openFileOutput("kuitit.txt", Context.MODE_PRIVATE));
            ows.write(t);
            ows.close();
            System.out.println("Writing was succesfull");
            System.out.println(context.getFilesDir());
        } catch ( IOException e){
            e.printStackTrace();
        }

    }


    public String returnMoney() {
        String print;
        if (money <= 0.0) {
            print = ("No money!");
        }
        else {
            print = ("Klink klink. Money came out! You got "+String.format("%.2f", money)+"€ back.");
            money = 0.0;

        }
        return print;
    }



}