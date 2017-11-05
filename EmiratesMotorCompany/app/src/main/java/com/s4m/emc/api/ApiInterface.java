package com.s4m.emc.api;

import com.s4m.emc.model.EMCData;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    /* Retrofit get annotation with URL and method that will return us the List of EMC data */
    @GET("EMC/IPDP/ipdpb.ashx?TemplateName=Promotions_ipad.htm&p=Common.Announcements&Handler=News&AppName=EMC&Type=News&F=J")
    Call<List<EMCData>> getJSON();
}
