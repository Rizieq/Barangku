package com.rizieq.barangku.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.rizieq.barangku.R;
import com.rizieq.barangku.detailTukar.DetailActivity;
import com.rizieq.barangku.model.barang.ResultItem;

import java.util.List;

public class AdapterBarang extends RecyclerView.Adapter<AdapterBarang.MyViewHolder> {

    private Context context;
    private List<ResultItem> barangItems;

    public AdapterBarang(Context context, List<ResultItem> barangItems) {
        this.context = context;
        this.barangItems = barangItems;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_tukar, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int i) {

        final ResultItem model = barangItems.get(i);
        /*final String urlImage = "http://192.168.70.95/db_barangku/";*/
        /*Glide.with(context).load(barangItems.get(i).getGambarBarang()).into(myViewHolder.imgBarang);*/
        Glide.with(context)
        .load("http://192.168.1.5/db_barangku/uploads/"+ model.getGambarBarang()).into(myViewHolder.imgBarang);
        Log.e(model.getGambarBarang(), "onBindViewHolder: ");

        myViewHolder.txtNamaBarang.setText(model.getNamaBarang());
        myViewHolder.txtStatus.setText(model.getStatus());
        myViewHolder.txtKondisi.setText(model.getKondisi());
        myViewHolder.txtStatusHarapan.setText(model.getStatusHarapan());
        myViewHolder.txtAlamat.setText(model.getAlamat());



        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("namaBarang",barangItems.get(i).getNamaBarang());
                intent.putExtra("kondisiBarang",barangItems.get(i).getKondisi());
                intent.putExtra("harapanBarang",barangItems.get(i).getHarapanBarang());
                intent.putExtra("dekskripsiBarang",barangItems.get(i).getDescBarang());
                intent.putExtra("gambarBarang","http://192.168.1.5/db_barangku/uploads/"+barangItems.get(i).getGambarBarang());
                intent.putExtra("alamatBarang",barangItems.get(i).getAlamat());

                intent.putExtra("id_tukar",barangItems.get(i).getIdTukar());
                intent.putExtra("respon_kurir",barangItems.get(i).getResponKurir());
                intent.putExtra("status",barangItems.get(i).getStatus());
                intent.putExtra("status_harapan",barangItems.get(i).getStatusHarapan());

                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return barangItems.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imgBarang;
        TextView txtNamaBarang, txtKondisi, txtStatus, txtStatusHarapan, txtAlamat;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imgBarang = (ImageView)itemView.findViewById(R.id.img_tukar_home);
            txtNamaBarang = (TextView)itemView.findViewById(R.id.txt_nama_barang_home);
            txtKondisi = (TextView)itemView.findViewById(R.id.txt_kondisi_home);
            txtStatus = (TextView)itemView.findViewById(R.id.txt_status_home);
            txtStatusHarapan = (TextView)itemView.findViewById(R.id.txt_status_harapan_home);
            txtAlamat = (TextView)itemView.findViewById(R.id.txt_alamat_home);



        }
    }
}
