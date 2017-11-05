package com.s4m.emc;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.s4m.emc.adapter.EMCDataAdapter;
import com.s4m.emc.api.ApiClient;
import com.s4m.emc.api.ApiInterface;
import com.s4m.emc.api.EMCDataClickListener;
import com.s4m.emc.model.EMCData;
import com.s4m.emc.utils.InternetConnection;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity implements EMCDataClickListener {

    private static final String TAG = HomeActivity.class.getSimpleName();

    private RecyclerView recyclerView;
    private EMCDataAdapter emcDataAdapter;
    ProgressDialog progressIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initViews();
    }

    private void initViews(){
        recyclerView = (RecyclerView)findViewById(R.id.card_recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        /**
         * Checking Internet Connection
         */
        if (InternetConnection.checkConnection(getApplicationContext())) {
            fetchDataFromServer();
        }
        else {
            Toast.makeText(HomeActivity.this, R.string.string_internet_connection_not_available,Toast.LENGTH_LONG).show();
        }
    }

    /** Fetching data from server **/
    public void fetchDataFromServer() {
        // Show progress indicator
        progressIndicator = new ProgressDialog(this);
        progressIndicator.setTitle(getResources().getString(R.string.string_fetching_data));
        progressIndicator.setMessage(getResources().getString(R.string.string_please_wait));
        progressIndicator.setCancelable(false);
        progressIndicator.show();

        //Creating an object of api interface
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        /**
         * Calling JSON
         */
        Call<List<EMCData>> call = apiService.getJSON();

        /**
         * Enqueue Callback will be call when get response...
         */
        call.enqueue(new Callback<List<EMCData>>() {
            @Override
            public void onResponse(Call<List<EMCData>> call, Response<List<EMCData>> response) {

                //Dismiss Dialog
                progressIndicator.dismiss();


                // Storing response in list
                List<EMCData> emcData = response.body();

                //Binding list to adapter
                emcDataAdapter = new EMCDataAdapter(emcData, HomeActivity.this);
                emcDataAdapter.setClickListener(HomeActivity.this);

                // Set adapter to recyclerview
                recyclerView.setAdapter(emcDataAdapter);
            }

            @Override
            public void onFailure(Call<List<EMCData>> call, Throwable error) {
                Log.e(TAG, error.getMessage());
            }
        });
    }

    @Override
    public void onItemClick(int position, EMCData item) {
        // Tapping on listview item to show in detailview
        Intent intent = new Intent(HomeActivity.this, EMCWebView.class);
        intent.putExtra("emchtmldata", item.getAnnouncementDescription().getDescriptionValue().toString());
        startActivity(intent);
    }
}
