package com.myapp.intellvest;

import com.google.firebase.database.Exclude;

public class ProductsModel {
    private String mKey;
    private int investors;
    private float commission;
    private int price;
    private int saleProduct;
    private String productName;
    private String productImageUrl;
    long millis;

    public ProductsModel() {
    }

    public ProductsModel(int investors, float commission, int price, int saleProduct, String productName, String productImageUrl, long millis) {
        this.investors = investors;
        this.commission = commission;
        this.price = price;
        this.saleProduct = saleProduct;
        this.productName = productName;
        this.productImageUrl = productImageUrl;
        this.millis = millis;
    }

    public int getInvestors() {
        return investors;
    }

    public void setInvestors(int investors) {
        this.investors = investors;
    }

    public float getCommission() {
        return commission;
    }

    public void setCommission(float commission) {
        this.commission = commission;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getSaleProduct() {
        return saleProduct;
    }

    public void setSaleProduct(int saleProduct) {
        this.saleProduct = saleProduct;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductImageUrl() {
        return productImageUrl;
    }

    public void setProductImageUrl(String productImageUrl) {
        this.productImageUrl = productImageUrl;
    }

    public long getMillis() {
        return millis;
    }

    public void setMillis(Long millis) {
        this.millis = millis;
    }

    @Exclude
    public String getKey() {
        return mKey;
    }
    @Exclude
    public void setKey(String key) {
        mKey = key;
    }
}
