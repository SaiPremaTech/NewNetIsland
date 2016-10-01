package com.example.mudit.newnetisland;

import android.Manifest;
import android.app.ActionBar;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;
import com.loopj.android.http.*;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity
{
    //GetJobDetails jobDetails;
    ImageButton passport, onapp, voterid, aadhar, recruitment, location, proprietor, otherservices;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       passport = (ImageButton)findViewById(R.id.passport_btn);
        //ActionBar bar = getActionBar();
        //bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#3f51b5")));
       onapp = (ImageButton)findViewById(R.id.onlineapplication_btn);
       voterid = (ImageButton)findViewById(R.id.voterid_btn);
       aadhar = (ImageButton)findViewById(R.id.aadharcard_btn);
       recruitment = (ImageButton)findViewById(R.id.recruitment_btn);
       location = (ImageButton)findViewById(R.id.location_btn);
       proprietor = (ImageButton)findViewById(R.id.proprietor_btn);
       otherservices = (ImageButton)findViewById(R.id.otherservices_btn);

       passport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Passport.class);
                startActivity(intent);
            }
        });

        voterid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, VoterId.class);
                startActivity(intent);
            }
        });

        aadhar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AadharCard.class);
                startActivity(intent);
            }
        });

        onapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, OnlineApplication.class);
                startActivity(intent);
            }
        });

        recruitment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Recruitment.class);
                startActivity(intent);
                //jobDetails = new GetJobDetails("History");
                //jobDetails.execute();
             /*   try {
                    String urlString = "http://ardevelopers.in/newnetisland.com/jobdetails.php";

                    RequestParams params = new RequestParams();
                    params.put("Action", "Query");
                    AsyncHttpClient client = new AsyncHttpClient();
                    client.get(urlString, params, new AsyncHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                            String s = new String(responseBody);
                            Log.v("Response", s);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                        }
                    });
                }
                catch (Exception e){
                    Toast.makeText(MainActivity.this, "Failure", Toast.LENGTH_LONG).show();
                }*/
            }
        });



        otherservices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, OtherServices.class);
                startActivity(intent);
            }
        });

       location.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(MainActivity.this,Location.class);
                startActivity(intent);
            }
        });

        proprietor.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(MainActivity.this,Proprietor.class);
                startActivity(intent);
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.call, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();
        if (id == R.id.call)
        {
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:9165668776"));
            startActivity(intent);
        }
        return true;
    }
}
