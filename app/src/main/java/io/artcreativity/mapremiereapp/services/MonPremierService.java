package io.artcreativity.mapremiereapp.services;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

public class MonPremierService extends Service {

    private MonBinder monBinder = new MonBinder();

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d("TAG Service", "onBind: " + intent.getStringExtra("name"));
        new Thread(new Runnable() {
            @Override
            public void run() {
                int start = 65;
                int limit = start + 30;
                do{
                    Log.d("TAG Service", "onBind: " + (char)start);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    start ++;
                }while(start<limit);

            }
        }).start();
        return monBinder;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, final int startId) {
        Log.d("TAG Service", "onStartCommand: " + intent.getStringExtra("name"));
        new Thread(new Runnable() {
            @Override
            public void run() {
                int start = 65;
                int limit = start + 30;
                do{
                    Log.d("TAG Service", "onStartCommand: " + (char)start);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    start ++;
                }while(start<limit);

                stopSelfResult(startId);
            }
        }).start();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.d("TAG Service", "onDestroy: ");
        super.onDestroy();
    }

    public class MonBinder extends Binder{

        public MonPremierService getService(){
            return MonPremierService.this;
        }
    }
}
