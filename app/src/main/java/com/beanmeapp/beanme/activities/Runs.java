package com.beanmeapp.beanme.activities;

import com.beanmeapp.beanme.R;
import com.beanmeapp.beanme.parsehelper.ParseToolbox;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class Runs extends ActionBarActivity {

    ListView listView ;
    List<String> groupNames;
    List<String> groupObjectIds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_runs);

        ActionBar ab = getSupportActionBar();
        ab.setTitle("Hosting Run");

        groupNames = new ArrayList<>();
        groupObjectIds = new ArrayList<>();

        listView = (ListView) findViewById(R.id.lv_invited);

        //Keeps the name and the id separate to make displaying the group names prettier.
        List<String> groups = ParseToolbox.getGroups();
        for (String group : groups) {
            groupObjectIds.add(group.split(ParseToolbox.GROUP_NAME_DELMITER)[0]);
            groupNames.add(group.split(ParseToolbox.GROUP_NAME_DELMITER)[1]);
        }

        Object[] values = groupNames.toArray();


        ArrayAdapter<Object> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, values);


        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                int itemPosition     = position;

                ParseToolbox.notifyGroup(groupObjectIds.get(itemPosition), groupNames.get(itemPosition));

                String  itemValue    = (String) listView.getItemAtPosition(position);

                /*
                Toast.makeText(getApplicationContext(),
                        "Position :" + itemPosition + "  ListItem : " + itemValue, Toast.LENGTH_LONG)
                        .show();
                */

            }

        });

        findViewById(R.id.invtbtn).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), GroupInvite.class));  // Launch the NFC 4.0 (SDK 14) Example
            }

        });



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_runs, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.addOrder) {
            startActivity(new Intent(getBaseContext(), DrinkDetails.class));
        }

        if (id == R.id.closeRun) {
            startActivity(new Intent(getBaseContext(), MainMenu.class));
        }

        if (id == R.id.setLeaving) {
            startActivity(new Intent(getBaseContext(), ViewDrinksList.class));
        }

        return super.onOptionsItemSelected(item);
    }
}
