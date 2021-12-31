package com.example.mpdamproject2021;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailsActivity extends AppCompatActivity {
    TextView detailPrice,detailsDescription,detailName, detailId;
    ImageView imageDetail,imageBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        imageBack=findViewById(R.id.image_back);
        imageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        detailName=findViewById(R.id.txt_name_Detail);
        detailPrice=findViewById(R.id.txt_price_detail);
        detailsDescription=findViewById(R.id.txt_descrip_detail);
        imageDetail=findViewById(R.id.img_details_product);
        Picasso.get().load(getIntent().getStringExtra("imageProduct")).into(imageDetail);
        detailName.setText(getIntent().getStringExtra("nameProduct"));
        detailPrice.setText(getIntent().getStringExtra("priceProduct"));
        detailsDescription.setText(getIntent().getStringExtra("descriptionProduct"));
    }
}