package com.yash.spacexcrew;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.yash.spacexcrew.adapters.CrewMemberAdapter;
import com.yash.spacexcrew.database.CrewMember;
import com.yash.spacexcrew.model.CrewMemberViewModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements CrewMemberAdapter.CrewMemberWikipediaClickHandler {

    private ProgressBar mProgressBar;
    private RecyclerView mRecyclerView;
    private CrewMemberAdapter mAdapter;
    private CrewMemberViewModel mViewModel;
    private TextView mNoInternetConnectionView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNoInternetConnectionView = findViewById(R.id.tv_no_internet_connection_view);

        mViewModel = new ViewModelProvider(this).get(CrewMemberViewModel.class);

        mProgressBar = findViewById(R.id.pb_loading_indicator);
        mRecyclerView = findViewById(R.id.rv_crew_member);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);
        mAdapter = new CrewMemberAdapter(this);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setHasFixedSize(false);
        if(isNetworkAvailable())
            fetchData();
        else {
            if(mViewModel.getAllCrewMembers() != null){
                setCrewMembersFromDatabase(mViewModel.getAllCrewMembers());
            } else {
                mRecyclerView.setVisibility(View.INVISIBLE);
                mNoInternetConnectionView.setVisibility(View.VISIBLE);
            }
        }
    }

    private void setCrewMembersFromDatabase(List<CrewMember> crewMembers) {
        ArrayList<CrewMemberModel> parsedData = new ArrayList<>();
        for(CrewMember crewMember:crewMembers) {
            parsedData.add(new CrewMemberModel(crewMember.imageUrl, crewMember.name,crewMember.agency, crewMember.status, crewMember.wikipediaUrl));
        }
        mAdapter.setCrewMemberData(parsedData);
        showRecyclerView();
    }

    private void showRecyclerView() {
        mRecyclerView.setVisibility(View.VISIBLE);
        mProgressBar.setVisibility(View.INVISIBLE);
    }

    private void hideRecyclerView() {
        mRecyclerView.setVisibility(View.INVISIBLE);
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onWikipediaClickListener(String wikipediaUrl) {
        Intent wikipediaIntent = new Intent(Intent.ACTION_VIEW);
        wikipediaIntent.setData(Uri.parse(wikipediaUrl));
        startActivity(wikipediaIntent);
    }

    private void fetchData() {
        hideRecyclerView();
        String url = "https://api.spacexdata.com/v4/crew/query";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.POST,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            setAdapterData(response);
                        } catch (JSONException e) {
                            Log.d("CrewMemRes", e.toString());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        mProgressBar.setVisibility(View.INVISIBLE);
                        Toast.makeText(MainActivity.this,"Error occurred", Toast.LENGTH_SHORT).show();
                        Log.d("CrewMemErr", error.toString());
                    }
                }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("user-agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.212 Safari/537.36");
                return params;
            }
        };

        RequestQueueSingleton.getInstance(getApplicationContext()).addToRequestQueue(jsonObjectRequest);
    }

    private void setAdapterData(JSONObject apiCallResult) throws JSONException {
        JSONArray docs = apiCallResult.getJSONArray("docs");
        JSONObject docsObject;
        ArrayList<CrewMemberModel> crewMemberData = new ArrayList<>();
        for (int i=0;i<docs.length(); i++) {
            docsObject = docs.getJSONObject(i);
            String crewMemberName = docsObject.getString("name");
            String crewMemberAgency = docsObject.getString("agency");
            String crewMemberStatus = docsObject.getString("status");
            String crewMemberImageUrl = docsObject.getString("image");
            String crewMemberWikipediaUrl = docsObject.getString("wikipedia");

            crewMemberData.add(new CrewMemberModel(crewMemberImageUrl, crewMemberName, crewMemberAgency, crewMemberStatus, crewMemberWikipediaUrl));
            if(!(mViewModel.crewMemberExists(crewMemberImageUrl)))
                mViewModel.insert(new CrewMember(crewMemberImageUrl, crewMemberName, crewMemberAgency, crewMemberStatus, crewMemberWikipediaUrl));
        }

        mAdapter.setCrewMemberData(crewMemberData);
        showRecyclerView();
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.action_refresh)
            fetchData();
        if(item.getItemId() == R.id.action_delete){
            mViewModel.delete();
        }
        return true;
    }
}