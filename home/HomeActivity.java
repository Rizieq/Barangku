package com.rizieq.barangku.home;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.rizieq.barangku.R;
import com.rizieq.barangku.create.InsertActivity;
import com.rizieq.barangku.createBarang.CreateActivity;
import com.rizieq.barangku.model.barang.ResultItem;
import com.rizieq.barangku.util.SessionManager;
import com.rizieq.barangku.model.*;
import com.rizieq.barangku.adapter.AdapterBarang;
import com.rizieq.barangku.api.RetroClient;

import com.rizieq.barangku.model.barang.ResponseBarang;
import com.rizieq.barangku.model.barang.ResultItem;
import com.rizieq.barangku.profil.ProfilActivity;
import com.rizieq.barangku.util.SessionManager;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {


    SessionManager sm;
    public HashMap<String, String> map;

    CardView cardProfil;
    ImageView imgProfile;
    TextView txtNama;
    FloatingActionButton floatAdd;

    RecyclerView rvBarang;
    private List<ResultItem> barangItems;
    /*String id;*/



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        rvBarang = findViewById(R.id.recyclerTukar);
        sm = new SessionManager(HomeActivity.this);

        txtNama = findViewById(R.id.txt_nama_profil_home);

        imgProfile = findViewById(R.id.imageView_home_profil);

        cardProfil = findViewById(R.id.card_profil);

        sm = new SessionManager(this);


        floatAdd = findViewById(R.id.float_add);

        floatAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, InsertActivity.class));
            }
        });


        cardProfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, ProfilActivity.class));
            }
        });


        // shrd pref
        map = sm.getDetailLogin();



        txtNama.setText(map.get(sm.KEY_NAMA));
        Glide.with(this)
                .load("http://192.168.1.5/db_barangku/uploads/"+map.get(sm.KEY_FOTO)).into(imgProfile);


        sm.checkLogin();


        getData();


        // notif

    /*    findViewById(R.id.btn_notif).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();

                calendar.set(Calendar.HOUR_OF_DAY, 00);
                calendar.set(Calendar.MINUTE, 04);
                calendar.set(Calendar.SECOND, 00);

                Intent intent = new Intent(getApplicationContext(), Notification_reciver.class);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 100, intent, PendingIntent.FLAG_UPDATE_CURRENT);

                AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
            }
        });*/


    }

    @Override
    protected void onResume() {
        super.onResume();
        getData();
    }

    private void getData() {

        RetroClient.getRequestService().getBarang(map.get(sm.KEY_ID)).enqueue(new Callback<ResponseBarang>() {
            @Override
            public void onResponse(Call<ResponseBarang> call, Response<ResponseBarang> response) {
                if (response.isSuccessful()) {
                    barangItems = response.body().getResult();
                    ResponseBarang responseBarang = response.body();
                    barangItems = responseBarang.getResult();
                    setUp(barangItems);

                }
            }

            @Override
            public void onFailure(Call<ResponseBarang> call, Throwable t) {
                Log.d("Error di sini bang", t.getMessage());
                Toast.makeText(HomeActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void setUp(List<ResultItem> barangItems) {
        rvBarang.setLayoutManager(new LinearLayoutManager(this));
        /*rvBarang.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));*/
        rvBarang.setHasFixedSize(true);
        rvBarang.setAdapter(new AdapterBarang(this, barangItems));
    }
}
