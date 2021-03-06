package com.example.mpdamproject2021;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mpdamproject2021.Model.Product;
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
        holder.priceProduct.setText(product.getPriceProduct()+" DT");
        Picasso.get().load(listProduct.get(position).getPictureProduct()).into(holder.pictureProduct);
        holder.pictureProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(context,DetailsActivity.class);
                int idProduct=product.getIdProduct();
                String id=String.valueOf(idProduct);

                String nameProduct=product.getNameProduct();
                String priceProduct=product.getPriceProduct()+" DT";
                String imageProduct=product.getPictureProduct();
                String descriptionProduct=product.getDescriptionProduct();
                intent.putExtra("idProduct",id);
                intent.putExtra("nameProduct",nameProduct);
                intent.putExtra("priceProduct",priceProduct);
                intent.putExtra("imageProduct",imageProduct);
                intent.putExtra("descriptionProduct",descriptionProduct);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listProduct.size();
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView pictureProduct;
        TextView idProduct,nameProduct,priceProduct,descriptionProduct;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            idProduct=itemView.findViewById(R.id.txt_product);
            pictureProduct = itemView.findViewById(R.id.img_product);
            nameProduct = itemView.findViewById(R.id.txt_name_prod);
            priceProduct = itemView.findViewById(R.id.txt_price_prod);
            descriptionProduct = itemView.findViewById(R.id.txt_descrip_prod);
        }
    }
}
