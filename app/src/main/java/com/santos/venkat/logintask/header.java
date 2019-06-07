package com.santos.venkat.logintask;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class header extends AppCompatActivity {
    RequestQueue requestQueue;
    TextView head;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_header);
        onGetHeader();
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        head=(TextView)findViewById(R.id.Header);

    }

    private void onGetHeader() {


        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("name", "kowtham");
            jsonObject.put("password", "kkk");
            jsonObject.put("name", "venkat");
            jsonObject.put("password", "santos");
            jsonObject.put("name", "guru");
            jsonObject.put("password", "abinesh");
        }
        catch (Exception e){

        }

        class JsonRequest extends JsonObjectRequest {

            public JsonRequest(int method, String url, JSONObject jsonRequest, Response.Listener
                    <JSONObject> listener, Response.ErrorListener errorListener) {
                super(method, url, jsonRequest, listener, errorListener);
            }

            @Override
            protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
                try {
                    String jsonString = new String(response.data,
                            HttpHeaderParser.parseCharset(response.headers, PROTOCOL_CHARSET));

                    JSONObject jsonResponse = new JSONObject();
                    jsonResponse.put("data", new JSONObject(jsonString));
                  //  jsonResponse.put("name", new JSONObject(jsonString));
                    jsonResponse.put("headers", new JSONObject(response.headers));
                    return Response.success(jsonResponse,
                            HttpHeaderParser.parseCacheHeaders(response));
                } catch (UnsupportedEncodingException e) {
                    return Response.error(new ParseError(e));
                } catch (JSONException je) {
                    return Response.error(new ParseError(je));
                }
            }
        }

        JsonRequest request = new JsonRequest
                (Request.Method.POST, "http://192.168.1.102:8083/setuser", jsonObject, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject data = response.getJSONObject("data");
                            JSONObject headers = response.getJSONObject("headers");
                            JSONObject name = response.getJSONObject("name");
                            Toast.makeText(getApplicationContext(),"comesin"+name,Toast.LENGTH_LONG).show();
                            head.setText(headers.toString());
                        } catch (JSONException e) {
                            Log.e("log", Log.getStackTraceString(e));
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("log", Log.getStackTraceString(error));
                    }

                })
                    {
                        @Override
                        public Map<String, String> getHeaders() throws AuthFailureError {
                            String a="header passed";
                            Map<String, String>  params = new HashMap<String, String>();

                            params.put("Authorisation",getName());

                            return params;
                        }
                        public String getName(){
                            return "venkat";
                        }
                    };
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(request);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                getActivity();

            case R.id.log_out:

                Toast.makeText(getApplicationContext(), "Log Out", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    private void getActivity() {
        Intent in = new Intent(header.this, viewprofile.class);
        startActivity(in);
        this.finish();

    }

    @Override
    public void onBackPressed() {
        getActivity();
        super.onBackPressed();
    }
}
