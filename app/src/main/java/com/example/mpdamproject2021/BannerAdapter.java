package com.example.mpdamproject2021;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class BannerAdapter  extends RecyclerView.Adapter<BannerAdapter.BannerHolder> {

    List<String> listUrls;
    Context context;
    public BannerAdapter(Context context,List<String>listUrls){
        this.context=context;
        this.listUrls=listUrls;
    }
    @NonNull
    @Override
    public BannerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.banner_item,parent,false);
        BannerAdapter.BannerHolder bannerViewHolder= new BannerAdapter.BannerHolder (view);
        return bannerViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BannerHolder holder, int position) {
        final String url =listUrls.get(position);
        Picasso.get().load(url).into(holder.imageBanner);
    }

    @Override
    public int getItemCount() {
        return listUrls.size();
    }

    public class BannerHolder extends RecyclerView.ViewHolder {
        ImageView imageBanner;
        public BannerHolder(View itemView) {
            super(itemView);
            imageBanner = itemView.findViewById(R.id.img_banner);
        }
    }
}
