package com.example.technoforrest.bulldogbites;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static android.R.attr.data;
import static android.R.attr.defaultValue;

public class FoodMenuActivity extends AppCompatActivity implements Serializable{
    final String TAG = "FoodMenuActivity";
    private List<Double> foodList;
    private int FOOD_REQUEST_CODE = 2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_menu);
        foodList = new ArrayList<>();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // get the menuinflater to inflate our menu
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.food_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
private void backMainMenu(){
    Intent intent = new Intent(this,MainActivity.class);
    startActivity(intent);
}
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case android.R.id.home:
                Log.d(TAG, "onOptionsItemSelected: home");
                backMainMenu();
                this.finish();
                break;

            case R.id.SaveMenuItem:
                Log.d(TAG, "onOptionsItemSelected: Save");
                Intent foodIntent = new Intent(FoodMenuActivity.this, MainActivity.class);
                foodIntent.putExtra("Total price", foodList.get(0).toString());
                setResult(Activity.RESULT_OK, foodIntent);
                finish();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }
    public void onButtonClicked(View view) {
        ImageView foodImage = (ImageView) view;
        if(foodImage.getTag().toString().compareTo("Pizza") == 0){
            Log.d(TAG, "onButtonClicked: ");
            FoodItem pizza = new PizzaItem();
            Intent intent = new Intent(FoodMenuActivity.this, PizzaActivity.class);
            startActivityForResult(intent, FOOD_REQUEST_CODE);
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == FOOD_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            Intent intent= getIntent();
            Log.d(TAG, "onActivityResult: got intent");;
            Double price = (Double) data.getSerializableExtra("Total price");
            foodList.add(price);
            //Log.d(TAG, "onActivityResult: " + price);

        }
    }

}
