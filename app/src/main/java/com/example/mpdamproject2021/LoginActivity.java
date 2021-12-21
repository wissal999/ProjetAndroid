package com.example.mpdamproject2021;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class LoginActivity extends AppCompatActivity {
TextView tv_incri;
EditText edtUsernameLogin,edtPasswordLogin;
ApiHandler apiHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        tv_incri=findViewById(R.id.txt_incri);
        tv_incri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), SignUpActivity.class);
                startActivity(intent);
            }
        });

        apiHandler= ApiClient.getApiClient().create(ApiHandler.class);
        edtUsernameLogin=findViewById(R.id.edt_username_login);
        edtPasswordLogin=findViewById(R.id.edt_password_login);
        SharedPreferences prefs= getPreferences(Context.MODE_PRIVATE);

        String emailsp=prefs.getString("email","");
        String passwordsp=prefs.getString("password","");
        if(emailsp.equals("")&& passwordsp.equals(""))
        {
            Toast.makeText(getApplicationContext(),"welcome",Toast.LENGTH_LONG).show();
        }
        else {
            Intent homeIntent=new Intent(getApplicationContext(),MainActivity.class);
             startActivity(homeIntent);
        }

        Button btnLogin =findViewById(R.id.btn_connexion);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                login();

            }
        });

    }
    public  void login(){
        String username=edtUsernameLogin.getText().toString().trim();
        String password=edtPasswordLogin.getText().toString().trim();
        if (username.isEmpty()) {
            edtUsernameLogin.setError("Email is required");
            edtUsernameLogin.requestFocus();
            return;
        }
        if (password.isEmpty()) {
            edtPasswordLogin.setError("Password required");
            edtPasswordLogin.requestFocus();
            return;
        }
        Call<User> LoginUser=apiHandler.loginUser(username,password);
        LoginUser.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Response<User> response, Retrofit retrofit) {
                if(response.body().getSuccess().equals("1")){
                    SharedPreferences prefs =getPreferences(Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor=prefs.edit();
                    editor.putString("username",edtUsernameLogin.getText().toString());
                    editor.putString("password",edtPasswordLogin.getText().toString());
                    editor.commit();
                    Intent homeIntent=new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(homeIntent);

                }
                else
                {
                    Toast.makeText(getApplicationContext(),"failed",Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(getApplicationContext(),"failed"+ t.getLocalizedMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }
    protected void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);
        outState.putString("email",edtUsernameLogin.getText().toString());
        outState.putString("pwd",edtPasswordLogin.getText().toString());
    }
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState)
    {
        super.onRestoreInstanceState(savedInstanceState);
        String email=savedInstanceState.getString("email");
        String password=savedInstanceState.getString("pwd");
        edtUsernameLogin.setText(email);
        edtPasswordLogin.setText(password);
    }
}