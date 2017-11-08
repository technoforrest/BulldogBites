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
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    final String TAG = "MainActivity";
    static final int MAIN_REQUEST_CODE = 1;
    private ArrayAdapter<FoodItem> arrayAdapter;
    private ListView listView;
    private String saveInstance = "items";
    private ArrayList<FoodItem> items;

    /**
     * saves the instance state
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: ");

        items = new ArrayList<>();
        if (savedInstanceState != null) {


            if (savedInstanceState.containsKey(saveInstance)) {

                items = (ArrayList<FoodItem>) savedInstanceState
                        .getSerializable(saveInstance);
                Log.d(TAG, "onRestoreInstanceState: inside" + items.toString());
            }
        }
        arrayAdapter = new ArrayAdapter<FoodItem>(this, android.R.layout.simple_list_item_activated_1, items);

        listView = new ListView(this);
        listView.setAdapter(arrayAdapter);


        setTitle("Bulldog Bites");

        // set the listview to support multiple selections
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        // set the multi choice listener
        listView.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {
            /**
             * creates the alert for deleting an order
             * @param actionMode
             * @param i
             * @param l
             * @param b
             */
            @Override
            public void onItemCheckedStateChanged(ActionMode actionMode, int i, long l, boolean b) {
                int numSelected = listView.getCheckedItemCount();
                actionMode.setTitle(numSelected + " selected");

                final int item = i;
                AlertDialog.Builder bd = new AlertDialog.Builder(MainActivity.this);
                bd.setTitle("Delete An Order");
                bd.setMessage("Are you sure you want to delete this order?");

                bd.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int listener) {
                        items.remove(item);
                        arrayAdapter.notifyDataSetChanged();
                    }
                });

                bd.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int listener) {
                        dialog.cancel();
                    }
                });
                AlertDialog alert = bd.create();
                alert.show();
            }

            /**
             * creates the menu
             * @param actionMode
             * @param menu
             * @return
             */
            @Override
            public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
                // inflate cam_menu
                MenuInflater menuInflater = actionMode.getMenuInflater();
                menuInflater.inflate(R.menu.cam_menu, menu);
                return true;
            }

            /**
             * @param actionMode
             * @param menu
             * @return
             */
            @Override
            public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {

                return true;
            }

            /**
             * @param actionMode
             * @param menuItem
             * @return
             */
            @Override
            public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
                return true;
            }

            /**
             * @param actionMode
             */
            @Override
            public void onDestroyActionMode(ActionMode actionMode) {

            }
        });

        setContentView(listView);
    }

    /**
     * opens up the food menu activity
     */
    private void startEditItemActivity() {
        // explicit intent
        Intent intent = new Intent(this, FoodMenuActivity.class);
        startActivityForResult(intent, MAIN_REQUEST_CODE);
    }

    /**
     * creates the delete alert dialog when garbage is clicked
     */
    private void deleteOrderActivity()
    {
        AlertDialog.Builder b = new AlertDialog.Builder(MainActivity.this);
        b.setTitle("Delete All Orders");
        b.setMessage("Are you sure you want to delete all of the orders?");

        b.setPositiveButton("YES", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int listener) {
                items.removeAll(items);
                arrayAdapter.notifyDataSetChanged();
            }
        });

        b.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int listener) {
                dialog.cancel();
            }
        });

        AlertDialog alert = b.create();
        alert.show();
    }

    /**
     * creates the about alert dialog when the three dots are clicked
     */
    private void aboutMenuActivity()
    {
        AlertDialog.Builder b = new AlertDialog.Builder(MainActivity.this);
        b.setTitle("About App");
        b.setMessage("This is a food menu app where you can order from a selection of food.");

        b.setPositiveButton("Done", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int listener) {
                dialog.cancel();
            }
        });

        AlertDialog alert = b.create();
        alert.show();

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
        menuInflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * creates the button functionality in the menu
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // called when the user clicks on one of our actions
        // add, preferences, about
        // first, figure out which item was clicked
        int menuId = item.getItemId();
        switch(menuId) {
            case R.id.addMenuItem:
                startEditItemActivity();
                return true;
            case R.id.deleteMenuItem:
                deleteOrderActivity();
                return true;
            case R.id.aboutMenuItem:
                aboutMenuActivity();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * adds a note to the list
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, "onActivityResult: ");
        if (requestCode == MAIN_REQUEST_CODE && resultCode == Activity.RESULT_OK) {

            FoodItem pizza= (PizzaItem) data.getSerializableExtra("Pizza");
            Log.d(TAG, "onActivityResult: ");
            items.add(pizza);//should be FOOD ITEM
            arrayAdapter.notifyDataSetChanged();


        }else Log.d(TAG, "onActivityResult: request code or result not ok" + RESULT_OK + " " + resultCode);
    }

    /**
     * saves the state
     * @param outState
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(saveInstance,  items);
        // call to super saves the state of
        // *some* of your views for you
        //outState.putStringArrayList(saveInstance, getAllItems());
    }




    /**
     *
     * @return
     */
    public ArrayList<FoodItem> getAllItems(){
        ArrayList<FoodItem> prices = new ArrayList<FoodItem>();
        for (int i = 0; i < items.size(); i++) {
            prices.add(items.get(i));
        }
        return prices;
    }


}