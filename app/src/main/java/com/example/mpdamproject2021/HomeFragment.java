package com.example.mpdamproject2021;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import static androidx.recyclerview.widget.LinearLayoutManager.HORIZONTAL;

public class HomeFragment extends Fragment {

    private RecyclerView recyclerView,recyclerViewBanner,recyclerView1;
    LinearLayoutManager layoutManager1,layoutManager2;
    RecyclerView.LayoutManager  layoutManager;

    Timer timer;
    TimerTask timerTask;
    int position;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_home, container, false);
        List<Produit> listeProduit= new ArrayList<>();
        listeProduit.add(new Produit(1,"Nutella",R.drawable.nutella));
        listeProduit.add(new Produit(2,"Ferrero",R.drawable.ferrero));
        listeProduit.add(new Produit(3,"Fromage",R.drawable.frommage));

        List<String> listeUrls= new ArrayList<>();
        listeUrls.add("https://previews.123rf.com/images/starlena/starlena1711/starlena171100054/90042033-affiche-publicitaire-pour-produit-cosm%C3%A9tique-pour-catalogue-magazine-conception-de-vecteur-de-paquet.jpg");
        listeUrls.add("https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.crushpixel.com%2Ffr%2Fstock-vector%2Fcosmetic-background-product-promo-advertising-269515.html&psig=AOvVaw3Fjz97dtct5olMrOjy2BR4&ust=1639346589430000&source=images&cd=vfe&ved=0CAsQjRxqFwoTCIjT_4_g3PQCFQAAAAAdAAAAABAN");
        listeUrls.add("https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.crushpixel.com%2Ffr%2Fstock-vector%2Fcosmetic-background-product-promo-advertising-269515.html&psig=AOvVaw3Fjz97dtct5olMrOjy2BR4&ust=1639346589430000&source=images&cd=vfe&ved=0CAsQjRxqFwoTCIjT_4_g3PQCFQAAAAAdAAAAABAN");

        recyclerView=v.findViewById(R.id.recycler_liste);
        recyclerViewBanner=v.findViewById(R.id.recycler_banner);
        recyclerView1=v.findViewById(R.id.recycler_listeMaquillage);

        layoutManager=new LinearLayoutManager(getActivity(), HORIZONTAL,false);
        layoutManager1=new LinearLayoutManager(getActivity(), HORIZONTAL,false);
        layoutManager2=new LinearLayoutManager(getActivity(), HORIZONTAL,false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView1.setLayoutManager(layoutManager2);
        recyclerViewBanner.setLayoutManager(layoutManager1);

        RecyclerViewAdapter produitAdapter=new RecyclerViewAdapter(getActivity(), listeProduit);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(produitAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView1.setHasFixedSize(true);
        recyclerView1.setAdapter(produitAdapter);
        recyclerView1.setItemAnimator(new DefaultItemAnimator());


        BannerAdapter bannerAdapter=new BannerAdapter(getActivity(), listeUrls);
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
                    position = layoutManager1.findFirstCompletelyVisibleItemPosition();
                    runAutoScrollBanner();
                }
            }
        });
        return v;
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
            position = layoutManager1.findFirstCompletelyVisibleItemPosition();
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
}