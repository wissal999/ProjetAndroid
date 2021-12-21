package com.example.mpdamproject2021;

public class Produit {

    public String nameProduct;
    public int imageProduct;
    public int idProduct;
    public int priceProduct;


    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public int getImageProduct() {
        return imageProduct;
    }

    public void setImageProduct(int imageProduct) {
        this.imageProduct = imageProduct;
    }

    public int getPriceProduct() {return priceProduct; }
    public void setPriceProduct(int priceProduct) {this.priceProduct = priceProduct; }

    public Produit( int idProduct,String nameProduct, int imageProduct) {
        this.idProduct = idProduct;
        this.nameProduct = nameProduct;
        this.imageProduct = imageProduct;

    }
}
