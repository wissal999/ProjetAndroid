package com.example.mpdamproject2021;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.mpdamproject2021.Model.Product;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class ScanActivity extends AppCompatActivity {
    ImageView imageScan,imageBack;
    String price;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);

        imageScan=findViewById(R.id.img_scan);
        imageBack=findViewById(R.id.image_back);
        imageScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scanCode();

            }
        });
        imageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }
    public void scanCode(){
        IntentIntegrator integrator=new IntentIntegrator(this);
        integrator.setCaptureActivity(CaptureAct.class);
        integrator.setOrientationLocked(false);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
        integrator.setPrompt("scanning code");
        integrator.initiateScan();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,  Intent data) {

        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() !=null){
                AlertDialog.Builder builder=new AlertDialog.Builder(this);
                ApiHandler apiHandler= ApiClient.getApiClient().create(ApiHandler.class);
                Call<Product> scanProduct=apiHandler.scanProduct(result.getContents());
                scanProduct.enqueue(new Callback<Product>() {
                    @Override
                    public void onResponse(Response<Product> response, Retrofit retrofit) {
                        if (response.body().getSuccess().equals("1") ) {
                            builder.setMessage(response.body().getPriceProduct()+" DT");
                            builder.setTitle("Prix de produit");
                            builder.setPositiveButton("Scan again", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    scanCode();
                                }
                            }).setNegativeButton("finish", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.cancel();
                                }
                            });
                            AlertDialog dialog=builder.create();
                            dialog.show();

                        }
                        else {
                            Toast.makeText(getApplicationContext(), "No data found  ", Toast.LENGTH_LONG).show();


                        }
                    }

                    @Override
                    public void onFailure(Throwable t) {

                    }
                });

            }
            else {
                Toast.makeText(this,"no resultats",Toast.LENGTH_LONG).show();
            }

        }else{
            super.onActivityResult(requestCode, resultCode, data);
        }


    }

}