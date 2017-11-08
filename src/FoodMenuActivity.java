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
import android.widget.ImageView;

import java.io.Serializable;


public class FoodMenuActivity extends AppCompatActivity implements Serializable{
    final String TAG = "FoodMenuActivity";

    private Boolean result = false;
    private int FOOD_REQUEST_CODE = 1;
    /**
     * saves the instance state and gets the back arrow
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_menu);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    /**
     *
     * @param menu the food_menu is passed and assigned
     * @return returns the menu that was created in xml food_menu to display
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // get the menuinflater to inflate our menu
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.food_menu, menu);
        return super.onCreateOptionsMenu(menu);

    }
    /**
     * goes back the the main activity
     */
    private void backFoodMenu(){
        Log.d(TAG, "backFoodMenu: ");
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        this.finish();
    }
    /**
     * assigns what each button of the menu does
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {

            case android.R.id.home:
                    backFoodMenu();
                    finish();
                    return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }
    /**
     * transitions from the food menu activity to the pizza activity
     * @param view
     */
    public void onButtonClicked(View view) {
        ImageView foodImage = (ImageView) view;
        if(foodImage.getTag().toString().compareTo("Pizza") == 0){
            Log.d(TAG, "onButtonClicked: ");
            Intent intent = new Intent(FoodMenuActivity.this, PizzaActivity.class);
            startActivityForResult(intent, FOOD_REQUEST_CODE);

        }
    }
    /**
     * gets information from the pizza activity
     * @param requestCode
     * @param resultCode
     * @param data
     */
   @Override
   protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == FOOD_REQUEST_CODE && resultCode == Activity.RESULT_OK) {

            //Send info to Main
            setResult(Activity.RESULT_OK,data);
            Log.d(TAG, "onActivityResult: finish second activity");
            finish();

        }
    }

}
