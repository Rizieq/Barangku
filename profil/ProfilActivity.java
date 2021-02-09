package com.rizieq.barangku.profil;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.rizieq.barangku.R;
import com.rizieq.barangku.model.barang.ResultItem;
import com.rizieq.barangku.util.SessionManager;

import java.util.HashMap;
import java.util.List;

public class ProfilActivity extends AppCompatActivity {

    TextView txtNama, txtEmail, txtAlamat, txtJenisKelamin, txtId;
    Button btnLogout;
    ImageView imgFoto;
    SessionManager sm;

    RecyclerView rvBarang;
    private List<ResultItem> barangItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        txtNama = findViewById(R.id.txt_nama_profil);
        txtEmail = findViewById(R.id.txt_email_profil);
        txtAlamat = findViewById(R.id.txt_alamat_profil);
        txtJenisKelamin = findViewById(R.id.txt_jenis_kelamin_profil);
        txtId = findViewById(R.id.txt_id_profil);

        imgFoto = findViewById(R.id.img_profil);

        btnLogout = findViewById(R.id.btn_logut);

        sm = new SessionManager(ProfilActivity.this);




        HashMap<String, String> map = sm.getDetailLogin();



        txtNama.setText(map.get(sm.KEY_NAMA));
        txtJenisKelamin.setText(map.get(sm.KEY_JK));
        txtEmail.setText(map.get(sm.KEY_EMAIL));
        txtAlamat.setText(map.get(sm.KEY_ALAMAT));
        txtId.setText(map.get(sm.KEY_FOTO));
        Glide.with(this)
                .load("http://192.168.70.88/db_barangku/uploads/"+map.get(sm.KEY_FOTO)).into(imgFoto);
        Log.d("RETRO", "onCreate: "+sm.KEY_JK);
/*        imgFoto.setImageResource(Integer.parseInt(map.get(sm.KEY_FOTO)));*/

        sm.checkLogin();

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sm.logout();
                sm.checkLogin();
            }
        });
    }
}
