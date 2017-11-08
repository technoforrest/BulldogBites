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

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;


import java.text.DecimalFormat;

public class PizzaActivity extends AppCompatActivity {
    private Boolean GF = false;
    private Boolean sauce = false;
    private Boolean pepperoni = false;
    private Boolean italianSausage = false;
    private Boolean chicken = false;
    private Boolean cheese = false;

    public TextView totalCalc;
    public TextView glutenFreeCalc;
    public TextView sauceCalc;
    public TextView pepperoniCalc;
    public TextView italianSausageCalc;
    public TextView chickenCalc;
    public TextView cheeseCalc;
    DecimalFormat decimalFormat = new DecimalFormat();

    private String TAG = "PizzaActivity";
    private double totalPrice = 9.99;
    private PizzaItem pizza;

    public double glutenFree = 0.00;
    public double s = 0.00;
    public double p = 0.00;
    public double i = 0.00;
    public double c = 0.00;
    public double progress = 0.00;

    /**
     * saves the instance and states the activity
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pizza);
        // enable the "up" functionality by adding the home action
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        totalCalc = (TextView) findViewById(R.id.totalEdit);
        totalCalc.setText("$" + decimalFormat.format(price()));
        setTitle("Build Your Own Pizza");

        if (savedInstanceState != null) {

            glutenFreeCalc = (TextView) findViewById(R.id.glutenPriceText);
            glutenFree = 3.00;
            glutenFreeCalc.setText("$" + decimalFormat.format(glutenFree) + ".00");

            sauceCalc = (TextView) findViewById(R.id.saucePriceText);
            s = 1.00;
            sauceCalc.setText("$" + decimalFormat.format(s) + ".00");
            totalCalc.setText("$" + decimalFormat.format(price()));

            pepperoniCalc = (TextView) findViewById(R.id.pepperoniPriceText);
            p = 0.50;
            pepperoniCalc.setText("$" + decimalFormat.format(p) + "0");

            italianSausageCalc = (TextView) findViewById(R.id.italianSausagePriceText);
            i = 1.00;
            italianSausageCalc.setText("$" + decimalFormat.format(i) + ".00");

            chickenCalc = (TextView) findViewById(R.id.chickenPriceText);
            c = 0.50;
            chickenCalc.setText("$" + decimalFormat.format(c) + "0");


            totalCalc.setText("$" + decimalFormat.format(price()));

        }


        SeekBar seekBarCalc = (SeekBar)findViewById(R.id.cheeseSeekBar);
        seekBarCalc.setMax(3);
        seekBarCalc.incrementProgressBy(1);

        cheeseCalc =(TextView)findViewById(R.id.cheesePriceText);
        cheeseCalc.setText("$" + progress + "0");
        /**
         * this function computes the amount and price of cheese for the pizza
         */
        seekBarCalc.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                progress = i;
                if(progress == 1)
                {
                    cheese = true;
                    cheeseCalc.setText("$" + progress + "0");
                    totalPrice += 1.00;
                    totalCalc.setText("$" + decimalFormat.format(price()));
                } else if(progress == 2) {
                    cheese = true;
                    cheeseCalc.setText("$" + progress + "0");
                    totalPrice += 2.00;
                    totalCalc.setText("$" + decimalFormat.format(price()));
                } else if(progress == 3){
                    cheese = true;
                    cheeseCalc.setText("$" + progress + "0");
                    totalPrice += 3.00;
                    totalCalc.setText("$" + decimalFormat.format(price()));
                }
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    /**
     * creates the menu
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // get the menuinflater to inflate our menu
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.pizza_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * creates button functionality in the menu
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Log.d(TAG, "onOptionsItemSelected: inside home");
                finish();
                return true;
            case R.id.SaveMenuItem:

                pizza = new PizzaItem(GF, sauce, pepperoni, italianSausage, chicken, cheese);
                Log.d(TAG, "onOptionsItemSelected: inside save");
                pizza.setPrice();
                totalPrice = pizza.getPrice();
                Intent newIntent = new Intent(PizzaActivity.this, FoodMenuActivity.class);

                newIntent.putExtra("Pizza", pizza);

                setResult(Activity.RESULT_OK, newIntent);
                Log.d(TAG, "onOptionsItemSelected: save finished");
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    /**
     * creates the switch button functionality
     * @param view
     */
    public void switchButtonClicked(View view) {
        Switch switchButton = (Switch) view;
        glutenFreeCalc = (TextView) findViewById(R.id.glutenPriceText);
        if (switchButton.isChecked()) {
            GF = true;
            glutenFree = 3.00;
            totalPrice += 3.00;
            glutenFreeCalc.setText("$" + decimalFormat.format(glutenFree) + ".00");
            totalCalc.setText("$" + decimalFormat.format(price()));
        } else {
            GF = false;
            glutenFree = 0.00;
            totalPrice -= 3.00;
            glutenFreeCalc.setText("$" + decimalFormat.format(glutenFree) + ".00");
            totalCalc.setText("$" + decimalFormat.format(price()));
        }
    }

    /**
     * creates the radio button functionality
     * @param view
     */
    public void radiobuttonClicked(View view) {
        boolean radioChecked = ((RadioButton) view).isChecked();
        sauceCalc = (TextView) findViewById(R.id.saucePriceText);

        switch (view.getId()) {
            case R.id.redSauceButton:
                if (radioChecked) {
                    sauce = false;
                    s = 0.00;
                    totalPrice -= 1.00;
                    sauceCalc.setText("$" + decimalFormat.format(s) + ".00");
                    totalCalc.setText("$" + decimalFormat.format(price()));
                }
                break;
            case R.id.whiteSauceButton:
                if (radioChecked) {
                    sauce = true;
                    s = 1.00;
                    totalPrice += 1.00;
                    sauceCalc.setText("$" + decimalFormat.format(s) + ".00");
                    totalCalc.setText("$" + decimalFormat.format(price()));
                }
        }
    }

    /**
     * creates the pepperoni check box functionality
     * @param view
     */
    public void onPepperoniCheckboxClicked(View view) {
        CheckBox pepperoniCheck = ((CheckBox) view);
        pepperoniCalc = (TextView) findViewById(R.id.pepperoniPriceText);
        if (pepperoniCheck.isChecked()) {
            pepperoni = true;
            p = 0.50;
            totalPrice += 0.50;
            pepperoniCalc.setText("$" + decimalFormat.format(p) + "0");
            totalCalc.setText("$" + decimalFormat.format(price()));
        } else {
            pepperoni = false;
            p = 0.0;
            totalPrice -= 0.50;
            pepperoniCalc.setText("$" + decimalFormat.format(p) + ".00");
            totalCalc.setText("$" + decimalFormat.format(price()));
        }
    }

    /**
     * creates the italian sausage check box functionality
     * @param view
     */
    public void onItalianSausageCheckboxClicked(View view) {
        CheckBox italianSausageCheck = ((CheckBox) view);
        italianSausageCalc = (TextView) findViewById(R.id.italianSausagePriceText);
        if (italianSausageCheck.isChecked()) {
            italianSausage = true;
            i = 1.00;
            totalPrice += 1.00;
            italianSausageCalc.setText("$" + decimalFormat.format(i) + ".00");
            totalCalc.setText("$" + decimalFormat.format(price()));
        } else {
            italianSausage = false;
            i = 0.0;
            totalPrice -= 1.00;
            italianSausageCalc.setText("$" + decimalFormat.format(i) + ".00");
            totalCalc.setText("$" + decimalFormat.format(price()));
        }
    }

    /**
     * creates the chicken check box functionality
     * @param view
     */
    public void onChickenCheckboxClicked(View view) {
        CheckBox chickenCheck = ((CheckBox) view);
        chickenCalc = (TextView) findViewById(R.id.chickenPriceText);
        if (chickenCheck.isChecked()) {
            chicken = true;
            c = 0.50;
            totalPrice += 0.50;
            chickenCalc.setText("$" + decimalFormat.format(c) + "0");
            totalCalc.setText("$" + decimalFormat.format(price()));
        } else {
            chicken = false;
            c = 0.0;
            totalPrice -= 0.50;
            chickenCalc.setText("$" + decimalFormat.format(c) + ".00");
            totalCalc.setText("$" + decimalFormat.format(price()));

        }

    }

    /**
     * gets the price
     * @return totalPrice
     */
    public double price()
    {
        return totalPrice;
    }
}