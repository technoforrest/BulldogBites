package com.example.technoforrest.bulldogbites;

import java.io.Serializable;
import java.text.DecimalFormat;


/**
 * Created by Danielle on 11/2/2017.
 */

public abstract class FoodItem implements Serializable{
    protected String foodType;
    protected Double price;
    DecimalFormat decimalFormat = new DecimalFormat();

    /**
     * default constructor
     */
    public FoodItem(){
        foodType = "Blank";
        price = 0.00;
    }

    /**
     *
     * @return string of food type and price
     */
    public String toString(){
        String foodStr = foodType + " order $" + decimalFormat.format(price);
        return foodStr;
    }

}
