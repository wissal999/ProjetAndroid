package com.example.mpdamproject2021;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.MyViewHolder> {

    List<Product> listProduct;
    Context context;
    public ProductAdapter(Context context, List<Product>listProduct){
        this.context=context;
        this.listProduct=listProduct;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
        MyViewHolder productViewHolder= new MyViewHolder(view);
        return productViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final Product product =listProduct.get(position);
        holder.idProduct.setText(String.valueOf(product.getIdProduct()));
        holder.nameProduct.setText(product.getNameProduct());
        holder.priceProduct.setText(product.getPriceProduct());
        Picasso.get().load(listProduct.get(position).getPictureProduct()).into(holder.pictureProduct);
    }

    @Override
    public int getItemCount() {
        return listProduct.size();
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView pictureProduct;
        TextView idProduct,nameProduct,priceProduct;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            idProduct=itemView.findViewById(R.id.txt_product);
            pictureProduct = itemView.findViewById(R.id.img_product);
            nameProduct = itemView.findViewById(R.id.txt_name_prod);
            priceProduct = itemView.findViewById(R.id.txt_price_prod);
        }
    }
}
