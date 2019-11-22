package io.artcreativity.mapremiereapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import io.artcreativity.mapremiereapp.entities.ItemDeMaListe;
import io.artcreativity.mapremiereapp.R;

public class MainActivity extends AppCompatActivity {
    public static final String MyTAG = "INSTI";
    private EditText clavier;
    private Button boutton;
    private TextView affichage;
    private ItemDeMaListe title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        clavier = findViewById(R.id.champ_saisi);
        boutton = findViewById(R.id.boutton);
        affichage = findViewById(R.id.affichage);
        Log.d(MyTAG, affichage.getText().toString());
        title = (ItemDeMaListe) getIntent().getSerializableExtra("ma_item");
        clavier.setText(title.title);
        affichage.setText(title.subTitle);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    public void jeClick(View view) {
        Log.d(MyTAG, "jeClick: ");
        String saisi = clavier.getText().toString();
        affichage.setText(saisi);
    }

    @Override
    public boolean onMenuOpened(int featureId, Menu menu) {
        Log.d(MyTAG, "onMenuOpened: ");
        return super.onMenuOpened(featureId, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Log.e(MyTAG, "onOptionsItemSelected: ");
        if(item.getItemId()==android.R.id.home){
            super.onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }


}
