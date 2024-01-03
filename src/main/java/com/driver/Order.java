package com.driver;

public class Order {

    private String id;
    private int deliveryTime;

    public Order(String id, String deliveryTime) {

        // The deliveryTime has to converted from string to int and then stored in the attribute
        //deliveryTime  = HH*60 + MM
        this.id = id;
        int hrs = Integer.parseInt(deliveryTime.substring(0,2))*60;
        int min = Integer.parseInt(deliveryTime.substring(3));
        int time = hrs + min;
        this.deliveryTime = time;
    }

    public String getDeliveryTimeAsString(int time){
        int hrs = time/60;
        int mins = time % 60;
        String hrString = "";
        String minString = "";
        if(hrs < 10) hrString = "0" + hrs;
        else hrString = "" + hrs;

        if(mins < 10) minString = "0" + mins;
        else minString = "" + mins;

        return hrString + ":" + minString;
    }

    public String getId() {
        return id;
    }

    public int getDeliveryTime() {return deliveryTime;}
}
