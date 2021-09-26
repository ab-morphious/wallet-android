package org.telegram.crypto.Retrofit;

import org.telegram.crypto.Models.Crypto;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIInterface {
    @GET("/v1/cryptocurrency/listings/latest")
    Call<Crypto> getCryptoList(@Query("start") int start,
                               @Query("limit") int limit,
                               @Query("convert") String convert,
                               @Query("CMC_PRO_API_KEY") String API_KEY);
}
