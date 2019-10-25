package io.artcreativity.mapremiereapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public static final String MyTAG = "INSTI";
    private EditText clavier;
    private Button boutton;
    private TextView affichage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        clavier = findViewById(R.id.champ_saisi);
        boutton = findViewById(R.id.boutton);
        affichage = findViewById(R.id.affichage);
        Log.d(MyTAG, affichage.getText().toString());
    }

    public void jeClick(View view) {
        Log.d(MyTAG, "jeClick: ");
        String saisi = clavier.getText().toString();
        affichage.setText(saisi);
    }
}
