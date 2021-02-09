package com.rizieq.barangku.createBarang;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.rizieq.barangku.R;
import com.rizieq.barangku.api.ApiRequest;
import com.rizieq.barangku.api.RetroClient;
import com.rizieq.barangku.home.HomeActivity;
import com.rizieq.barangku.model.barang.ResponseModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateActivity extends AppCompatActivity {


    TextView namaBarang, kondisiBarang, statusBarang, dekskripsiBarang, harapanBarang;
    Button btnCreate;
    ProgressDialog pd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);


        namaBarang = findViewById(R.id.edt_nama_barang);
        kondisiBarang = findViewById(R.id.edt_kondisi_barang);
        statusBarang = findViewById(R.id.edt_status_barang);
        dekskripsiBarang = findViewById(R.id.edt_dekskripsi_barang);
        harapanBarang = findViewById(R.id.edt_harapan_barang);

        btnCreate = findViewById(R.id.btn_create_barang);

        pd = new ProgressDialog(this);


        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pd.setMessage("Loading ...");
                pd.setCancelable(false);
                pd.show();


                String snamaBarang = namaBarang.getText().toString();
                String skondisiBarang = kondisiBarang.getText().toString();
                String sstatusBarang = statusBarang.getText().toString();
                String sdekskripsiBarang = dekskripsiBarang.getText().toString();
                String sharapanBarang = harapanBarang.getText().toString();



                ApiRequest api = RetroClient.getClient().create(ApiRequest.class);
                Call<ResponseModel> sendBarang = api.createBarang(snamaBarang,skondisiBarang,sstatusBarang,sdekskripsiBarang,sharapanBarang);
                sendBarang.enqueue(new Callback<ResponseModel>() {
                    @Override
                    public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                        pd.hide();
                        Log.d("RETRO", "onResponse: "+response.body().toString());
                        String kode = response.body().getKode();
                        if (kode.equals("1"))
                        {
                            Toast.makeText(CreateActivity.this, "Data Berhasil Disimpan", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(CreateActivity.this, HomeActivity.class));
                        }
                        else
                        {
                            Toast.makeText(CreateActivity.this, "Data gagal disimpan", Toast.LENGTH_SHORT).show();
                        }


                    }

                    @Override
                    public void onFailure(Call<ResponseModel> call, Throwable t) {
                        pd.hide();
                        Log.d("RETRO", "onFailure: " + "Gagal Mengirim Request");

                    }
                });


            }
        });
    }
}
