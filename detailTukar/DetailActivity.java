package com.rizieq.barangku.detailTukar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.rizieq.barangku.R;

public class DetailActivity extends AppCompatActivity {

    ImageView imgBarang;
    TextView txtNamaBarang, txtKondisi, txtStatus, txtDesc, txtHarapan, txtStatusHarapan, txtResponKurir, txtIdBarang, txtAlamat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        imgBarang = findViewById(R.id.img_tukar_detail);
        txtNamaBarang = findViewById(R.id.txt_nama_barang_detail);
        txtKondisi = findViewById(R.id.txt_kondisi_detail);
        txtStatus = findViewById(R.id.txt_status_detail);
        txtDesc = findViewById(R.id.txt_desc_barang_detail);
        txtHarapan = findViewById(R.id.txt_harapan_detail);
        txtStatusHarapan = findViewById(R.id.txt_status_harapan);
        txtResponKurir = findViewById(R.id.txt_respon_kurir);
        txtAlamat = findViewById(R.id.txt_alamat_barang_detail);



        getDataDetail();

    }

    @Override
    protected void onResume() {
        super.onResume();
        getDataDetail();
    }

    private void getDataDetail() {

        Glide.with(this)
                .load(getIntent().getStringExtra("gambarBarang")).into(imgBarang);
        txtNamaBarang.setText(getIntent().getStringExtra("namaBarang"));
        txtKondisi.setText(getIntent().getStringExtra("kondisiBarang"));
        txtDesc.setText(getIntent().getStringExtra("dekskripsiBarang"));
        txtHarapan.setText(getIntent().getStringExtra("harapanBarang"));
        txtAlamat.setText(getIntent().getStringExtra("alamatBarang"));


        txtStatus.setText(getIntent().getStringExtra("status"));
        txtStatusHarapan.setText(getIntent().getStringExtra("status_harapan"));
        txtResponKurir.setText(getIntent().getStringExtra("respon_kurir"));
    }
}
