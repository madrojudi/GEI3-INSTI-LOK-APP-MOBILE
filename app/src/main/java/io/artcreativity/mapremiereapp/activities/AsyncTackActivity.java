package io.artcreativity.mapremiereapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.IBinder;
import android.widget.TextView;

import io.artcreativity.mapremiereapp.R;
import io.artcreativity.mapremiereapp.services.MonPremierService;

public class AsyncTackActivity extends AppCompatActivity {

    private TextView ecran;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_tack);
        ecran = findViewById(R.id.ecran);
        MonCompteurTask monCompteurTask = new MonCompteurTask();
        monCompteurTask.execute(30, 5);
    }
    MonPremierService monPremierService;
    ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            monPremierService = ((MonPremierService.MonBinder)iBinder).getService();
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = new Intent(this, MonPremierService.class);
        intent.putExtra("name", "Mon Service");
        bindService(intent, serviceConnection, BIND_AUTO_CREATE);


    }

    public class MonCompteurTask extends AsyncTask<Integer, Integer, Void>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            ecran.setText("0");
        }

        @Override
        protected Void doInBackground(Integer... params) {
            /*la premiere valeur de notre params represente le
            * nombre de seconde que nous allons compter
            * Alors on prend la premiere valeur comme la
            * limite de notre compteur**/

            int compteur = 0;
            do {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                compteur++;
                publishProgress(compteur);
            } while(compteur<params[0]);
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            ecran.setText(values[0] + "");
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            ecran.setText(ecran.getText().toString() + "\nDone!");
        }
    }
}
