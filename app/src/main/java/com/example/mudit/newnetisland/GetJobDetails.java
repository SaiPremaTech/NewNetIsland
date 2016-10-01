package com.example.mudit.newnetisland;

/**
 * Created by Mudit on 30-05-2016.
 */
import android.os.AsyncTask;
import android.util.Log;
import android.widget.EditText;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.Map;


@SuppressWarnings("ResourceType")
public class GetJobDetails extends AsyncTask<String, Void, String> {
    private String GetAction;


    // Constructor
    public GetJobDetails(String Action) {
        GetAction = Action;
    }

    private static final String TAG = "Job Details";

    public String getStringFromInputStream(InputStream stream) throws IOException
    {
        int n = 0;
        char[] buffer = new char[1024 * 4];
        InputStreamReader reader = new InputStreamReader(stream, "UTF8");
        StringWriter writer = new StringWriter();
        while (-1 != (n = reader.read(buffer))) writer.write(buffer, 0, n);
        return writer.toString();
    }

    @Override
    protected String doInBackground(String... params) {
        String dummy = "DUMMY";

        try {
            URL url = new URL("http://www.kmantapa.com/Updateprice.php");
            Map<String, Object> updateJobDetails = new LinkedHashMap<>();
            updateJobDetails.put("Action", GetAction);

            StringBuilder postData = new StringBuilder();
            for (Map.Entry<String, Object> param : updateJobDetails.entrySet()) {
                if (postData.length() != 0) postData.append('&');
                postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
                postData.append('=');
                postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
            }
            byte[] postDataBytes = postData.toString().getBytes("UTF-8");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
            conn.setDoOutput(true);
            conn.getOutputStream().write(postDataBytes);

            String resp = getStringFromInputStream(conn.getInputStream());
            Log.i(TAG, "Response" + "\n" + resp);
        }
        catch (Exception e) {
            Log.e("HTTP POST Request Error", "Failed to send HTTP Request for getting job details" + e.toString());
        }
        return dummy;
    }

    protected void onPostExecute(String page)
    {
        //onPostExecute
    }
}
