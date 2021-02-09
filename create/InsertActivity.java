package com.rizieq.barangku.create;

import android.Manifest;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.rizieq.barangku.R;
import com.rizieq.barangku.api.ApiRequest;
import com.rizieq.barangku.api.RetroClient;
import com.rizieq.barangku.home.HomeActivity;
import com.rizieq.barangku.model.insert.ImageClass;
import com.rizieq.barangku.util.SessionManager;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InsertActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edtNamaBarang, edtKondisi, edtStatusBarang, edtDekskripsiBarang, edtHarapanBarang, edtResponKurir, edtStatusHarapanBarang, edtAlamatBarang, edtNohandphoneBarang;
    private Button BnChoose,BnUpload;
    private ImageView image;
    private static final int IMG_REQUEST = 777;
    private Bitmap bitmap;
    private Uri path;
    private ApiRequest apiInterface = RetroClient.getClient().create(ApiRequest.class);
    public static final int STORAGE_PERMISSION_CODE = 1;

    public HashMap<String,String> map;
    SessionManager sm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);


        edtNamaBarang = findViewById(R.id.edt_nama_barang_create);
        edtKondisi = findViewById(R.id.edt_kondisi_barang_create);
        edtStatusBarang = findViewById(R.id.edt_status_barang_create);
        edtDekskripsiBarang = findViewById(R.id.edt_desc_barang_create);
        edtHarapanBarang = findViewById(R.id.edt_harapan_barang_create);
        edtResponKurir = findViewById(R.id.edt_respon_kurir_create);
        edtStatusHarapanBarang = findViewById(R.id.edt_status_harapan_create);
        edtAlamatBarang = findViewById(R.id.edt_alamat_create);
        edtNohandphoneBarang = findViewById(R.id.edt_noHandphone_barang);



        BnChoose = (Button)findViewById(R.id.chooseBn);
        BnUpload = (Button)findViewById(R.id.uploadBn);


        image = (ImageView)findViewById(R.id.image);

        BnChoose.setOnClickListener(this);
        BnUpload.setOnClickListener(this);

        // shrd pref
        sm = new SessionManager(this);

        map = sm.getDetailLogin();

        PermissionGalery();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.chooseBn:
                selectImage();
                break;
            case R.id.uploadBn:
                uploadImage();
                /*postImage();*/

                break;
        }

    }

    private void uploadImage() {

        String namaBarang = edtNamaBarang.getText().toString();
        String kondisiBarang = edtKondisi.getText().toString();
        String statusBarang = edtStatusBarang.getText().toString();
        String dekskripsiBarang = edtDekskripsiBarang.getText().toString();
        String harapanBarang = edtHarapanBarang.getText().toString();
        String responBarang = edtResponKurir.getText().toString();
        String statusHarapanBarang = edtStatusHarapanBarang.getText().toString();
        String alamatBarang = edtAlamatBarang.getText().toString();
        String nohandphoneBarang = edtNohandphoneBarang.getText().toString();
        String id = map.get(sm.KEY_ID);
        // cek data dengan LOG
        Log.d("RETRO", "uploadImage: "+id);

        /*RequestBody mId = RequestBody.create(MediaType.parse("multipart/form-data"),id);*/
        RequestBody mNamaBarang = RequestBody.create(MediaType.parse("multipart/form-data"),namaBarang);
        RequestBody mKondisiBarang = RequestBody.create(MediaType.parse("multipart/form-data"),kondisiBarang);
        RequestBody mStatusBarang = RequestBody.create(MediaType.parse("multipart/form-data"),statusBarang);
        RequestBody mDekskripsiBarang = RequestBody.create(MediaType.parse("multipart/form-data"),dekskripsiBarang);
        RequestBody mHarapanBarang = RequestBody.create(MediaType.parse("multipart/form-data"),harapanBarang);
        RequestBody mResponBarang = RequestBody.create(MediaType.parse("multipart/form-data"),responBarang);
        RequestBody mStatusHarapanBarang = RequestBody.create(MediaType.parse("multipart/form-data"),statusHarapanBarang);
        RequestBody mAlamatBarang = RequestBody.create(MediaType.parse("multipart/form-data"),alamatBarang);
        RequestBody mNoHandphone = RequestBody.create(MediaType.parse("multipart/form-data"),nohandphoneBarang);


        // Mengambil alamat file image
        File myFile = new File(path.getPath());
        Uri selectedImage = getImageContentUri(this, myFile, path);
        String partImage = getPath(this, selectedImage);
        File imageFile = new File(partImage);

        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), imageFile);
        MultipartBody.Part mPartImage = MultipartBody.Part.createFormData("image", imageFile.getName(), requestBody);

        Call<ImageClass> call = apiInterface.uploadImage(
                mNamaBarang,
                mKondisiBarang,
                mStatusBarang,
                mDekskripsiBarang,
                mHarapanBarang,
                mResponBarang,
                mStatusHarapanBarang,
                mAlamatBarang,
                mNoHandphone,
                mPartImage,
                Integer.valueOf(map.get(sm.KEY_ID)));

        call.enqueue(new Callback<ImageClass>() {
            @Override
            public void onResponse(Call<ImageClass> call, Response<ImageClass> response) {
                if (response.body() != null){
                    Log.d("RETRO", "onResponse: "+response.body().getMessage());
                    Toast.makeText(InsertActivity.this, "Berhasil", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(InsertActivity.this, HomeActivity.class));
                }
            }

            @Override
            public void onFailure(Call<ImageClass> call, Throwable t) {
                Toast.makeText(InsertActivity.this, "Gagal", Toast.LENGTH_SHORT).show();
                Log.d("RETRO ", "onFailure: " + t.getMessage());
            }
        });



    }




    private void selectImage()
    {
        // ini untuk select gambar
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,IMG_REQUEST);
    }

    // ini untuk menampilkan gambar yangdi pilih
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==IMG_REQUEST && resultCode==RESULT_OK && data!=null)
        {
            path = data.getData();

            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),path);
                image.setImageBitmap(bitmap);
                image.setVisibility(View.VISIBLE);

                BnChoose.setEnabled(false);
                BnUpload.setEnabled(true);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private String imageToString()
    {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
        byte[] imgByte = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(imgByte,Base64.DEFAULT);

    }
    private String getPath(Context context, Uri filePath) {

        Cursor cursor = context.getContentResolver().query(filePath, null, null, null, null);
        cursor.moveToFirst();
        String document_id = cursor.getString(0);
        document_id = document_id.substring(document_id.lastIndexOf(":") + 1);
        cursor.close();

        cursor = context.getContentResolver().query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                null, MediaStore.Images.Media._ID + " = ? ",
                new String[]{document_id}, null);
        cursor.moveToFirst();
        String path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
        cursor.close();

        return path;
    }

    private void PermissionGalery() {
        // Mencek apakah user sudah memberikan permission untuk mengakses external storage
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
            return;

        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
            //If the user has denied the permission previously your code will come to this block
            //Here you can explain why you need this permission
            //Explain here why you need this permission
        }
        //And finally ask for the permission
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, InsertActivity.STORAGE_PERMISSION_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == InsertActivity.STORAGE_PERMISSION_CODE) {
            //If permission is granted
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //Displaying a toast
                showMessage("Permission granted now you can read the storage");
                Log.i("Permission on", "onRequestPermissionsResult: " + String.valueOf(grantResults));
            } else {
                //Displaying another toast if permission is not granted
                showMessage("Oops you just denied the permission");
                Log.i("Permission off", "onRequestPermissionsResult: " + String.valueOf(grantResults));

            }
        }
    }

    private void showMessage(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }


    private Uri getImageContentUri(Context context, File imageFile, Uri filePath) {

        String fileAbsolutePath = imageFile.getAbsolutePath();
        Cursor cursor = context.getContentResolver().query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                new String[]{MediaStore.Images.Media._ID},
                MediaStore.Images.Media.DATA + "=? ",
                new String[]{fileAbsolutePath}, null);

        if (cursor != null && cursor.moveToFirst()) {
            // Apabila file gambar sudah pernah diapakai namun ada kondisi lain yang belum diketahui
            // Apabila file gambar sudah pernah dipakai pengambilan bukan di galery

            Log.i("Isi Selected if", "Masuk cursor ada");
            return filePath;

        } else {
            Log.i("Isi Selected else", "cursor tidak ada");
            if (imageFile.exists()) {
                // Apabila file gambar baru belum pernah di pakai
                Log.i("Isi Selected else", "imagefile exists");
                ContentValues values = new ContentValues();
                values.put(MediaStore.Images.Media.DATA, fileAbsolutePath);
                return context.getContentResolver().insert(
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
            } else {
                // Apabila file gambar sudah pernah dipakai
                // Apabila file gambar sudah pernah dipakai di galery
                Log.i("Isi Selected else", "imagefile tidak exists");
                return filePath;
            }
        }
    }
}

