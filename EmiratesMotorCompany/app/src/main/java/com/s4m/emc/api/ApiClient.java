package com.s4m.emc.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    /** Base Url **/
    public static final String BASE_URL = "http://94.56.199.34/";
    private static Retrofit retrofit = null;

    /**
     * Get Retrofit Instance
     */
    public static Retrofit getClient() {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
