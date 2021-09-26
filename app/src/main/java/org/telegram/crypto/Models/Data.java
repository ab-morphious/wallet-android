package org.telegram.crypto.Models;

import com.google.gson.annotations.SerializedName;

public class Data {

    //Response Model for Data
    @SerializedName("id")
    public int id;
    @SerializedName("name")
    public String name;
    @SerializedName("symbol")
    public String symbol;
    @SerializedName("slug")
    public String slug;
    @SerializedName("quote")
    public Quote quote;

    //Table titles constructor
    private String titleId;
    private String titleName;
    private String titlePrice;
    private String titlePercentage24H;
    private String titlePercentage7D;
    private String titleMarketCap;
    private String titleVolume24H;

    public Data(String titleId, String titleName, String titlePrice, String titlePercentage24H, String titlePercentage7D, String titleMarketCap, String titleVolume24H) {
        this.titleId = titleId;
        this.titleName = titleName;
        this.titlePrice = titlePrice;
        this.titlePercentage24H = titlePercentage24H;
        this.titlePercentage7D = titlePercentage7D;
        this.titleMarketCap = titleMarketCap;
        this.titleVolume24H = titleVolume24H;
    }
    public String getTitleId() {
        return titleId;
    }
    public String getTitleName() {
        return titleName;
    }
    public String getTitlePrice() {
        return titlePrice;
    }
    public String getTitlePercentage24H() { return titlePercentage24H; }
    public String getTitlePercentage7D() { return titlePercentage7D; }
    public String getTitleMarketCap() { return titleMarketCap; }
    public String getTitleVolume24H() { return titleVolume24H; }
}
