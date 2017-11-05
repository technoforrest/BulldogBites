package com.example.technoforrest.bulldogbites;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.data;

public class MainActivity extends AppCompatActivity {
    final String TAG = "MainActivity";
    static final int MAIN_REQUEST_CODE = 1;
    private List<String> items;
    private ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: ");

        items = new ArrayList<>();

        arrayAdapter =
                new ArrayAdapter<String>(
                        this,
                        android.R.layout.simple_list_item_activated_1,
                        items
                );

        final ListView listView = new ListView(this);
        listView.setAdapter(arrayAdapter);
        // set the listview to support multiple selections
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        // set the multi choice listener
        listView.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {
            @Override
            public void onItemCheckedStateChanged(ActionMode actionMode, int i, long l, boolean b) {
                int numSelected = listView.getCheckedItemCount();
                actionMode.setTitle(numSelected + " selected");
            }

            @Override
            public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
                // inflate cam_menu
                MenuInflater menuInflater = actionMode.getMenuInflater();
                menuInflater.inflate(R.menu.cam_menu, menu);
                return true;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {

                return true;
            }

            @Override
            public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
                return true;
            }

            @Override
            public void onDestroyActionMode(ActionMode actionMode) {

            }
        });

        setContentView(listView);
    }

    private void startEditItemActivity() {
        // explicit intent
        Intent intent = new Intent(this,
                FoodMenuActivity.class);
        startActivityForResult(intent, MAIN_REQUEST_CODE);
    }

    // inflate the menu in main_menu.xml
    // we do this by overriding a create method

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // get the menuinflater to inflate our menu
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

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
            // task: add the other cases
            // show toast
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, "onActivityResult: ");
        if (requestCode == MAIN_REQUEST_CODE && resultCode == Activity.RESULT_OK) {

            String price = data.getStringExtra("Total price");
            items.add(price);
            arrayAdapter.notifyDataSetChanged();


        }else Log.d(TAG, "onActivityResult: request code or result not ok");
    }
    /**
     * checks to see if the note already exists in the list
     * @param newPrice
     * @return true or false
     */
    public boolean doesNoteExist(String newPrice){
        for(int i = 0; i < items.size(); i++){
            if(items.get(i).equals(newPrice))
                return false;
        }
        return true;
    }
}
