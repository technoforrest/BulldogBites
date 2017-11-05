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
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.text.DecimalFormat;

public class PizzaActivity extends AppCompatActivity {
    private Boolean GF;
    private int sauce;
    private String TAG = "PizzaActivity";
    private double totalPrice = 0;
    private PizzaItem pizza;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pizza);

        Intent intent = getIntent();

        // enable the "up" functionality by adding the home action
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // get the menuinflater to inflate our menu
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.food_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    private void backFoodMenu(){
        Intent intent = new Intent(this,FoodMenuActivity.class);
        startActivity(intent);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case android.R.id.home:
                Log.d(TAG, "onOptionsItemSelected: home");
                backFoodMenu();
                this.finish();
                break;
            case R.id.SaveMenuItem:
                pizza = new PizzaItem(GF, sauce);
                Log.d(TAG, "onOptionsItemSelected: save");
                pizza.setPrice();//crashes here, why???
                totalPrice = pizza.getPrice();
                Intent newIntent = new Intent(PizzaActivity.this,FoodMenuActivity.class);
                Log.d(TAG, "Price in PizzaActivity" + totalPrice);
                Bundle b = new Bundle();
                b.putSerializable("Total price", totalPrice);
                newIntent.putExtras(b);
                setResult(Activity.RESULT_OK, newIntent);
                finish();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }
    public void switchButtonClicked(View view) {
        // we know view is a Switch
        Switch switchButton = (Switch) view;
        // we can find out it it is "checked" or not
        if (switchButton.isChecked()) {
            // on state
            GF = true;
            Toast.makeText(this, "Switch button on", Toast.LENGTH_SHORT).show();
        }
        else {
            GF = false;
            // off state
            Toast.makeText(this, "Switch button off", Toast.LENGTH_SHORT).show();
        }
    }
    public void radiobuttonClicked(View view) {
        // task: add code to write to the logs about which radio button was clicked
        // let's get a reference to RadioGroup

        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.classStandingRadioGroup);
        int radioButtonID = radioGroup.getCheckedRadioButtonId(); // this will tell us which
        // radiobutton is checked
        sauce = radioButtonID;

    }

/*    public void seekBarClicked(View view)
    {
        seekBarCalc = (SeekBar)findViewById(R.id.cheeseSeekBar);
        cheeseCalc =(TextView)findViewById(R.id.cheesePriceText);
        cheeseCalc.setText(seekBarCalc.getProgress());

        seekBarCalc.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {

                    int progress_value;
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        progress_value = progress;
                        cheeseCalc.setText(progress);
                        Toast.makeText(PizzaActivity.this,"SeekBar in progress",Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {
                        Toast.makeText(PizzaActivity.this,"SeekBar in StartTracking",Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                        cheeseCalc.setText(progress_value);
                        Toast.makeText(PizzaActivity.this,"SeekBar in StopTracking",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }*/


}
