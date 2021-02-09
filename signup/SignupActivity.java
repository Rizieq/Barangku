package com.rizieq.barangku.signup;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.rizieq.barangku.R;
import com.rizieq.barangku.api.ApiRequest;
import com.rizieq.barangku.api.RetroClient;
import com.rizieq.barangku.login.LoginActivity;
import com.rizieq.barangku.model.user.ResponseApiModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignupActivity extends AppCompatActivity {

    Button btnSignUp;
    ProgressDialog pd;
    EditText edtEmail, edtPassword, edtUsername, edtAlamat, edtGender, edtNoHandphone, edtNama;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        edtNama = findViewById(R.id.edtNama);
        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
        edtUsername = findViewById(R.id.edtUsername);
        edtAlamat = findViewById(R.id.edtAlamat);
        edtGender= findViewById(R.id.edtJenisKelamin);
        edtNoHandphone= findViewById(R.id.edtNoHandphone);

        btnSignUp = findViewById(R.id.btnSignUp);



        pd = new ProgressDialog(this);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pd.setMessage("send data ... ");
                pd.setCancelable(false);
                pd.show();

                String snama = edtNama.getText().toString();
                String semail = edtEmail.getText().toString();
                String spassword = edtPassword.getText().toString();
                String susername = edtUsername.getText().toString();
                String salamat = edtAlamat.getText().toString();
                String sgender = edtGender.getText().toString();
                String snohandphone = edtNoHandphone.getText().toString();
                ApiRequest api = RetroClient.getClient().create(ApiRequest.class);

                Call<ResponseApiModel> sendbio = api.signup(semail,spassword,snama,snohandphone,sgender,susername,salamat);
                sendbio.enqueue(new Callback<ResponseApiModel>() {
                    @Override
                    public void onResponse(Call<ResponseApiModel> call, Response<ResponseApiModel> response) {
                        pd.hide();
                        Log.d("RETRO", "response : " + response.body().toString());
                        String kode = response.body().getKode();

                        if(kode.equals("1"))
                        {
                            Toast.makeText(SignupActivity.this, "Data berhasil disimpan", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(SignupActivity.this, LoginActivity.class));
                        }else
                        {
                            Toast.makeText(SignupActivity.this, "Data Error tidak berhasil disimpan", Toast.LENGTH_SHORT).show();

                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseApiModel> call, Throwable t) {
                        pd.hide();
                        Log.d("RETRO", "Failure : " + "Gagal Mengirim Request");
                    }
                });
            }
        });


    }
}
