package com.rizieq.barangku.login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.rizieq.barangku.R;
import com.rizieq.barangku.api.ApiRequest;
import com.rizieq.barangku.api.RetroClient;
import com.rizieq.barangku.home.HomeActivity;
import com.rizieq.barangku.model.user.ResponseApiModel;
import com.rizieq.barangku.model.user.UserModel;
import com.rizieq.barangku.signup.SignupActivity;
import com.rizieq.barangku.util.SessionManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private EditText edtEmail, edtPassword;
    private Button btnLogin;
    private TextView btnSignUp;
    private ProgressDialog pd;
    private static final String TAG = LoginActivity.class.getSimpleName();
    private SessionManager sm;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);

        btnLogin = findViewById(R.id.btnLogin);
        /*btnSignUp = findViewById(R.id.btnSignUp);*/
        btnSignUp = findViewById(R.id.btnSignUp);

        pd = new ProgressDialog(LoginActivity.this);
        sm = new SessionManager(LoginActivity.this);
        pd.setMessage("loading ...");

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, SignupActivity.class));
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pd.show();
                ApiRequest Api = RetroClient.getRequestService();
                Call<ResponseApiModel> login = Api.login(edtEmail.getText().toString(),edtPassword.getText().toString());
                login.enqueue(new Callback<ResponseApiModel>() {
                    @Override
                    public void onResponse(Call<ResponseApiModel> call, Response<ResponseApiModel> response) {
                        pd.dismiss();
                        Log.d(TAG, "response : "+ response.toString());
                        ResponseApiModel res = response.body();
                        List<UserModel> user = res.getResult();
                        if (res.getKode().equals("1")){
                            // this shared preference respon
                            sm.storeLogin(user.get(0).getNama(),
                                    user.get(0).getJenisKelamin(),
                                    user.get(0).getAlamat(),
                                    user.get(0).getEmail(),
                                    user.get(0).getFotoSignup(),
                                    user.get(0).getId());
                            Log.d("RETRO", "onResponse: "+user.get(0).getFotoSignup());


                            Toast.makeText(LoginActivity.this, "login sukses", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                            startActivity(intent);


                        } else {
                            Toast.makeText(LoginActivity.this, "email/password tidak cocok", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseApiModel> call, Throwable t) {
                        pd.dismiss();
                        Log.e(TAG, "error: "+ t.getMessage());
                    }
                });

            }
        });

    }
}
