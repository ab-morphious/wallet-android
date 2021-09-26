package org.telegram.crypto.Models;

import com.google.gson.annotations.SerializedName;

public class USD {
    public double price;
    @SerializedName("volume_24h")
    public double volume24h;
    @SerializedName("percent_change_1h")
    public float percentageChange1h;
    @SerializedName("percent_change_24h")
    public float percentageChange24h;
    @SerializedName("percent_change_7d")
    public float percentageChange7d;
    @SerializedName("percent_change_30d")
    public float percentageChange30d;
    @SerializedName("percent_change_60d")
    public float percentageChange60d;
    @SerializedName("percent_change_90d")
    public float percentageChange90d;
    @SerializedName("market_cap")
    public double marketCap;
    @SerializedName("market_cap_dominance")
    public float marketCapDominance;
    @SerializedName("fully_diluted_market_cap")
    public double fullyDilutedMarketCap;
}
