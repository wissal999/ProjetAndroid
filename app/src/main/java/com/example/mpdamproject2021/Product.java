package com.example.mpdamproject2021;


import com.google.gson.annotations.SerializedName;

import java.math.BigInteger;

public class Product {
    public int idProduct;
    public String nameProduct;
    public String pictureProduct;
    public String priceProduct;
    public BigInteger barcode;

    private String success;

    public Product(int idProduct, String nameProduct, String pictureProduct, String priceProduct, BigInteger barcode) {
        this.idProduct = idProduct;
        this.nameProduct = nameProduct;
        this.pictureProduct = pictureProduct;
        this.priceProduct = priceProduct;
        this.barcode = barcode;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

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

    public String getPictureProduct() {
        return pictureProduct;
    }

    public void setPictureProduct(String pictureProduct) {
        this.pictureProduct = pictureProduct;
    }

    public String getPriceProduct() {
        return priceProduct;
    }

    public void setPriceProduct(String priceProduct) {
        this.priceProduct = priceProduct;
    }

    public BigInteger getBarcode() {
        return barcode;
    }

    public void setBarcode(BigInteger barcode) {
        this.barcode = barcode;
    }
}
