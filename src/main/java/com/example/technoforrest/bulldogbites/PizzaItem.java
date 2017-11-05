package com.example.technoforrest.bulldogbites;

/**
 * Created by Danielle on 11/2/2017.
 */

public class PizzaItem extends FoodItem{
    private double price;
    private Boolean glutenBool = false;
    private int sauce;

    public PizzaItem(){

    }
    public PizzaItem(Boolean GF, int newSauce){
        price = 9.99;
        glutenBool = GF;
        sauce = newSauce;

    }
    public void setPrice(){
        if(glutenBool){
            price += 2.00;
        }



    }
    public double getPrice(){
        return price;
    }

}
