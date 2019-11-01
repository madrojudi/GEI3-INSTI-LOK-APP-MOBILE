package io.artcreativity.mapremiereapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.util.Log;
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
//    private GridView gridView;
    List<ItemDeMaListe> items = new ArrayList<>();
    String TAG = "MainListActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_list);
        final List<Map<String, String>> data = new ArrayList<>();
//        gridView = findViewById(R.id.grid);
        listView = findViewById(R.id.list);
        generateList();
        for (int i= 0; i<items.size(); i++){
            Map<String, String> map1 = new HashMap<>();
            map1.put("title", items.get(i).title);
            map1.put("subtitle", items.get(i).subTitle);
            data.add(map1);

        }
        String[] entete = new String[]{"title", "subtitle"};
        int[] idres = new int[]{R.id.mon_text, R.id.mon_text2};
        ListAdapter  listAdapter = new SimpleAdapter(this, data, R.layout.item, entete, idres);
        ListAdapter  listAdapter2 = new SimpleAdapter(this, data, R.layout.item, entete, idres);
//        gridView.setAdapter(listAdapter);
        listView.setAdapter(listAdapter2);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Map<String, String> map = data.get(i);
                Toast.makeText(MainListActivity.this, map.get("title"), Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainListActivity.this, MainActivity.class);
                intent.putExtra("ma_item", items.get(i));
                startActivity(intent);
            }
        });

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
        Log.d(TAG, "onStart: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: ");
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
}
