package com.example.mpdamproject2021;

import static androidx.recyclerview.widget.LinearLayoutManager.HORIZONTAL;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.mpdamproject2021.Model.Product;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerViewMode,recyclerViewMaquillage,recyclerViewBebe,recyclerViewSuperette,recyclerViewBanner;
    LinearLayoutManager layoutManagerBanner;
    RecyclerView.LayoutManager  layoutManager;

    Timer timer;
    TimerTask timerTask;
    int position;
    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView=(BottomNavigationView) findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(naviglistener);

        List<String> listeUrls= new ArrayList<>();
        listeUrls.add("https://previews.123rf.com/images/starlena/starlena1803/starlena180300047/97601855-cartel-publicitario-para-producto-cosm%C3%A9tico-con-rosa-para-cat%C3%A1logo-revista-dise%C3%B1o-vectorial-de-paque.jpg");
        listeUrls.add("https://www.lejournaldecrapette.fr/wp-content/uploads/2018/10/parfum-joy-dior-avis.jpg");
        listeUrls.add("https://previews.123rf.com/images/starlena/starlena1803/starlena180300049/97601858-cartel-publicitario-para-producto-cosm%C3%A9tico-con-rosa-para-cat%C3%A1logo-revista-dise%C3%B1o-vectorial-de-paque.jpg");

        recyclerViewMode=findViewById(R.id.recyclerview_mode);
        recyclerViewMaquillage=findViewById(R.id.recyclerview_maquillage);
        recyclerViewBebe=findViewById(R.id.recyclerview_bebe);
        recyclerViewSuperette=findViewById(R.id.recyclerview_superette);

        recyclerViewBanner=findViewById(R.id.recycler_banner);
        layoutManagerBanner=new LinearLayoutManager(getApplicationContext(), HORIZONTAL,false);
        recyclerViewBanner.setLayoutManager(layoutManagerBanner);
        fetchProduct(recyclerViewMode,"mode");
        fetchProduct(recyclerViewMaquillage,"maquillage");
        fetchProduct(recyclerViewBebe,"bebe");
        fetchProduct(recyclerViewSuperette,"superette");


        BannerAdapter bannerAdapter=new BannerAdapter(getApplicationContext(), listeUrls);
        recyclerViewBanner.setHasFixedSize(true);
        recyclerViewBanner.setAdapter(bannerAdapter);

        if (listeUrls!=null){
            position=Integer.MAX_VALUE/2;
            recyclerViewBanner.scrollToPosition(position);
        }

        SnapHelper snapHelper=new LinearSnapHelper();
        snapHelper.attachToRecyclerView(recyclerViewBanner);
        recyclerViewBanner.smoothScrollBy(5,0);

        recyclerViewBanner.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (newState == 1) {
                    stopAutoScrollBanner();
                } else if (newState == 0) {
                    position = layoutManagerBanner.findFirstCompletelyVisibleItemPosition();
                    runAutoScrollBanner();
                }
            }
        });
    }
    @Override
    public void onResume() {
        super.onResume();
        runAutoScrollBanner();
    }

    @Override
    public void onPause() {
        super.onPause();
        stopAutoScrollBanner();
    }

    private void stopAutoScrollBanner() {
        if (timer != null && timerTask != null) {
            timerTask.cancel();
            timer.cancel();
            timer = null;
            timerTask = null;
            position = layoutManagerBanner.findFirstCompletelyVisibleItemPosition();
        }
    }
    private void runAutoScrollBanner(){
        if (timer==null && timerTask==null){
            timer=new Timer();
            timerTask=new TimerTask() {
                @Override
                public void run() {
                    if (position == Integer.MAX_VALUE) {
                        position = Integer.MAX_VALUE / 2;
                        recyclerViewBanner.scrollToPosition(position);
                        recyclerViewBanner.smoothScrollBy(5, 0);
                    } else {
                        position++;
                        recyclerViewBanner.smoothScrollToPosition(position);
                    }
                }

            };
            timer.schedule(timerTask,4000,4000);
        }
    }
    public void fetchProduct(RecyclerView recyclerView,String category){
        ApiHandler apiHandler= ApiClient.getApiClient().create(ApiHandler.class);
        Call<List<Product>> getProduct=apiHandler.getProducts(category);
        getProduct.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Response<List<Product>> response, Retrofit retrofit) {
                List<Product> listProduct;
                if (response.isSuccess()) {
                    listProduct = response.body();
                    layoutManager=new LinearLayoutManager(getApplicationContext(), HORIZONTAL,false);
                    recyclerView.setLayoutManager(layoutManager);
                    ProductAdapter ProductAdapter = new ProductAdapter(getApplicationContext(),listProduct);
                    recyclerView.setAdapter( ProductAdapter);

                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }

    public BottomNavigationView.OnNavigationItemSelectedListener naviglistener=new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch (item.getItemId()){

                case R.id.item_home:

                    break;
                case R.id.item_cart:

                    break;
                case R.id.item_person:
                    Intent intent1=new Intent(getApplicationContext(),SettingsActivity.class);
                    startActivity(intent1);
                    break;
                case R.id.item_scan:
                    Intent intent=new Intent(getApplicationContext(),ScanActivity.class);
                    startActivity(intent);
                    break;



            }

            return true;
        }
    };

}