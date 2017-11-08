package com.example.technoforrest.bulldogbites;

/**
 * This program implements a food menu to order pizaa from
 * CPSC 312-01, Fall 2017
 * Programming Assignment #6
 *
 * @author Holly Schwartz and Danielle Forrest
 * @version v1.0 11/06/17
 *
 * contributions:
 *      worked together on every step of the project splitting the work up evenly
 */

public class PizzaItem extends FoodItem {

    private Boolean glutenBool = false;
    private Boolean sauce = false;
    private Boolean pepperoni = false;
    private Boolean italianSausage = false;
    private Boolean chicken = false;
    private Boolean cheese = false;
    private String pizza = "pizza";


    /**
     * constructor
     */
    public PizzaItem() {
        this.price = 9.99;
        this.foodType = "Pizza";
    }

    /**
     * constructor
     *
     * @param GF
     * @param S
     * @param PP
     * @param IS
     * @param CK
     * @param CH
     */
    public PizzaItem(Boolean GF, Boolean S, Boolean PP, Boolean IS, Boolean CK, Boolean CH) {
        this();
        glutenBool = GF;
        sauce = S;
        pepperoni = PP;
        italianSausage = IS;
        chicken = CK;
        cheese = CH;


    }

    /**
     * creates the price
     */
    public void setPrice() {
        if (glutenBool) {
            price += 3.00;
        }

        if (sauce) {
            price += 1.00;
        }

        if (pepperoni) {
            price += 0.50;
        }

        if (italianSausage) {
            price += 1.00;
        }

        if (chicken) {
            price += 0.50;
        }

        if (cheese) {
            price += 3.00;
        }

    }

    /**
     * gets the price
     *
     * @return
     */
    public double getPrice() {
        return price;
    }

}