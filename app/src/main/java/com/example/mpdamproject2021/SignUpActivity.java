package com.example.mpdamproject2021;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mpdamproject2021.Model.User;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class SignUpActivity extends AppCompatActivity {
    TextView tvConnect;
    EditText edtFullname;
    EditText edtUsername;

    EditText edtPhone;
    EditText edtEmail;
    EditText edtPassword;
    Button btnAjouter;
    ApiHandler apiHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        tvConnect=findViewById(R.id.txt_connection);
        tvConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

        apiHandler= ApiClient.getApiClient().create(ApiHandler.class);
        edtFullname= (EditText)findViewById(R.id.edt_nomComplet);
        edtUsername = (EditText)findViewById(R.id.edt_username);
        edtPhone = (EditText)findViewById(R.id.edt_telephone);
        edtEmail = (EditText)findViewById(R.id.edt_email);
        edtPassword = (EditText)findViewById(R.id.edt_mdp);
        btnAjouter = (Button)findViewById(R.id.btn_inscription);
        btnAjouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addUser();
            }
        });
    }
    private void addUser() {
        String fullname=edtFullname.getText().toString().trim();
        String username=edtUsername.getText().toString().trim();
        String phone=edtPhone.getText().toString().trim();
        String email=edtEmail.getText().toString().trim();
        String password=edtPassword.getText().toString().trim();
        Call<User> insertUser=apiHandler.insertUser(fullname,username,phone,email,password);
        insertUser.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Response<User> response, Retrofit retrofit) {
                Toast.makeText(getApplicationContext(),"user added",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(getApplicationContext(),"failed " + t.getLocalizedMessage(),Toast.LENGTH_LONG).show();

            }
        });
    }
}