package org.telegram.crypto.Models;

import com.google.gson.annotations.SerializedName;

public class ResponseStatus {
    @SerializedName("error_code")
    public int errorCode;
    @SerializedName("error_message")
    public String errorMessage;
}
