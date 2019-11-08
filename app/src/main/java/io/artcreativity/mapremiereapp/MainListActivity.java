package io.artcreativity.mapremiereapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.DataSetObserver;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainListActivity extends AppCompatActivity {

    private ListView listView;
    private MonAdapter  listAdapter2;
    List<ItemDeMaListe> items = new ArrayList<>();
    String TAG = "MainListActivity";
    SharedPreferences prefs;
    int selection;

    private final int CREATE_ITEM_OPTION = 25;

    public static final String SELECTION_KEY = "SELECTION";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_list);
        listView = findViewById(R.id.list);
        generateList();
        listAdapter2 = new MonAdapter(this, items);
        prefs = getSharedPreferences(TAG, Activity.MODE_PRIVATE);
        selection = prefs.getInt(SELECTION_KEY, -1);
        listAdapter2.selectItem(selection);
    }

    public void generateList(){
        for (int i= 0; i<40; i++){
            ItemDeMaListe itemDeMaListe = new ItemDeMaListe("Mon titre " + (i+1), "Mon sous titre " + (i+1));
            items.add(itemDeMaListe);
        }
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        Log.d(TAG, "onPostResume: ");
    }

    @Override
    protected void onStart() {
        super.onStart();
        listView.setAdapter(listAdapter2);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                listAdapter2.selectItem(i);
                selection = i;
                ItemDeMaListe map = items.get(i);
                Toast.makeText(MainListActivity.this, map.title, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainListActivity.this, MainActivity.class);
                intent.putExtra("ma_item", items.get(i));
                startActivity(intent);
            }
        });
        registerForContextMenu(listView);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: ");
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(SELECTION_KEY, selection);
        editor.apply();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = new MenuInflater(this);
        inflater.inflate(R.menu.menu_list, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.add_item_menu:
                Intent intent = new Intent(this, CreateItemActivity.class);
                startActivityForResult(intent, CREATE_ITEM_OPTION);
                break;
            case R.id.setting_item_menu:
                intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == CREATE_ITEM_OPTION){
            if(resultCode == Activity.RESULT_OK && data!=null){
                ItemDeMaListe item = (ItemDeMaListe) data.getSerializableExtra(CreateItemActivity.ITEM_CREATED);
                listAdapter2.addItem(item);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater menuInflater = new MenuInflater(this);
        menuInflater.inflate(R.menu.contextuel_menu, menu);
//        Log.e(TAG, "onCreateContextMenu: " + (R.id.list == v.getId()) );
        if(!listView.canScrollVertically(-1)) {
            menu.getItem(1).setVisible(false);
        }
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.refresh_item_menu:
                listAdapter2.notifyDataSetInvalidated();
                break;
            case R.id.go_to_top_item_menu:
                listView.smoothScrollToPosition(0);
                break;
        }
        return super.onContextItemSelected(item);
    }
}
