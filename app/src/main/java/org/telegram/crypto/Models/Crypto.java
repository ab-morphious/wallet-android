package org.telegram.crypto.Models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/* General API Response model class, nested json structure is decomposed with
* corresponding model classes*/
public class Crypto {
    @SerializedName("status")
    public ResponseStatus status;
    @SerializedName("data")
    public List<Data> data;
}
