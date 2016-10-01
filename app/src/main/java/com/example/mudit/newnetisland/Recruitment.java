package com.example.mudit.newnetisland;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Recruitment extends AppCompatActivity {
    TextView resultView;
    HttpURLConnection urlConnection = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recruitment);

        new Thread(new Runnable() {
            @Override
            public void run() {
                getData();
            }
        }).start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.call, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.call) {
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:9165668776"));
            startActivity(intent);
        }
        return true;
    }

    public void getData() {
        //String result = "";
        InputStream isr = null;
        try {
            /*HttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost("url should be written here");
            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            isr = entity.getContent();*/

            URL url = new URL("http://46.105.28.241:443/:table");
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.connect();
            isr = urlConnection.getInputStream();

        } catch (Exception e) {
            Log.e("log tag", "Error in http connection" + e.toString());
            resultView.setText("couldnt connect to database");
        }

        //convert response to string
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(isr, "iso-8859-1"), 8);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            isr.close();

            final String result = sb.toString();
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    parsedata(result);
                }
            });


        } catch (Exception e) {
            Log.e("log tag", "Error converting result" + e.toString());
        }
    }

      /*  //parse json data
        try
        {
            String s = "";
            JSONArray jArray = new JSONArray(result);


            for(int i = 0; i<jArray.length(); i++) {
                JSONObject json = jArray.getJSONObject(i);
                s = s + "Serial No : " + json.getInt("serial_no") + "\n"+
                        "Job Id : " + json.getString("jobid") + "\n"+
                        "Job Description : " + json.getString("jobdescription")+"\n";
            }
                resultView.setText(s);

        }
        catch (Exception e)
        {
            Log.e("log tag", "error parsing data" +e.toString());
        }
    }*/

    //parse json data

    public void parsedata(String data) {
        try {
            JSONArray jArray = new JSONArray(data);
            TableLayout tv = (TableLayout) findViewById(R.id.table);
            tv.removeAllViewsInLayout();
            int flag = 1;
            for (int i = -1; i < jArray.length() - 1; i++) {
                TableRow tr = new TableRow(Recruitment.this);
                tr.setLayoutParams(new TableRow.LayoutParams(
                        TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.WRAP_CONTENT));
                if (flag == 1) {
                    TextView b6 = new TextView(Recruitment.this);
                    b6.setText("Id");
                    b6.setTextColor(Color.BLUE);
                    b6.setTextSize(20);
                    tr.addView(b6);
                    TextView b19 = new TextView(Recruitment.this);
                    b19.setPadding(20, 0, 0, 0);
                    b19.setTextSize(20);
                    b19.setText("Job Id");
                    b19.setTextColor(Color.BLUE);
                    tr.addView(b19);
                    TextView b29 = new TextView(Recruitment.this);
                    b29.setPadding(20, 0, 0, 0);
                    b29.setText("Job Description");
                    b29.setTextColor(Color.BLUE);
                    b29.setTextSize(20);
                    tr.addView(b29);
                    tv.addView(tr);
                    final View vline = new View(Recruitment.this);
                    vline.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, 2));
                    vline.setBackgroundColor(Color.BLUE);
                    tv.addView(vline);
                    flag = 0;
                } else {
                    JSONObject json_data = jArray.getJSONObject(i);
                    Log.i("log tag", "Serial No :" + json_data.getInt("serial_no") + "Job Id:"
                            + json_data.getString("jobid") + "Job Description:" + json_data.getString("jobdescription"));
                    TextView b = new TextView(Recruitment.this);
                    String stime = String.valueOf(json_data.getInt("serial_no"));
                    b.setText(stime);
                    b.setTextColor(Color.RED);
                    b.setTextSize(18);
                    tr.addView(b);
                    TextView b1 = new TextView(Recruitment.this);
                    b1.setPadding(20, 0, 0, 0);
                    b1.setTextSize(18);
                    String stime1 = json_data.getString("jobid");
                    b1.setText(stime1);
                    b1.setTextColor(Color.BLACK);
                    tr.addView(b1);
                    TextView b2 = new TextView(Recruitment.this);
                    b2.setPadding(20, 0, 0, 0);
                    String stime2 = json_data.getString("jobdescription");
                    b2.setText(stime2);
                    b2.setTextColor(Color.BLACK);
                    b2.setTextSize(18);
                    tr.addView(b2);
                    tv.addView(tr);
                    final View vline1 = new View(Recruitment.this);
                    vline1.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, 1));
                    vline1.setBackgroundColor(Color.WHITE);
                    tv.addView(vline1);
                }
            }
        } catch (JSONException e) {
            Log.e("log_tag", "error parsing data" + e.toString());
            Toast.makeText(getApplicationContext(), "Json array failed", Toast.LENGTH_SHORT).show();
        }


    }
}
