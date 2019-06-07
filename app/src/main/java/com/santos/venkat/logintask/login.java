package com.santos.venkat.logintask;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
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
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class login extends AppCompatActivity {
    TextView tv;
    TextInputLayout ed1,ed2;
    Button logIn;
    RequestQueue requestQueue;
    ImageView imv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View decorView = getWindow().getDecorView();
// Hide the status bar.
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        setContentView(R.layout.login);
        tv=(TextView)findViewById(R.id.textView6);
        ed1=(TextInputLayout)findViewById(R.id.username);
        ed2=(TextInputLayout)findViewById(R.id.password);
        logIn=(Button)findViewById(R.id.logIn);
       // imv=(ImageView)findViewById(R.id.imageView);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in=new Intent(login.this, signup.class);
                startActivity(in);
                finish();
            }
        });
        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               // Toast.makeText(getApplicationContext(),"Comes In..",Toast.LENGTH_SHORT).show();
                //Toast.makeText(getApplicationContext(),"Name:"+ed1.getEditText().getText()+"\n"+"Password:"+ed2.getEditText().getText(),Toast.LENGTH_LONG).show();
                onGetHeader();

            }
        });

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
                           // JSONObject name = response.getJSONObject("name");
                            Toast.makeText(getApplicationContext(),"Name:"+ed1.getEditText().getText()+"\n"+"Password:"+ed2.getEditText().getText()+"\n"+headers.toString(),Toast.LENGTH_LONG).show();
                            ed1.setHelperText(headers.toString());
                            if(headers.toString()!=null){
                                Intent in =new Intent(login.this, viewprofile.class);
                                startActivity(in);
                                finish();
                            }

                        } catch (JSONException e) {
                            Log.e("log", Log.getStackTraceString(e));
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_LONG).show();
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


   /* public void onSendRequest(){
        //String url="http://localhost:8083/setuser";


        *//*JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Method.POST, "http://192.168.1.102/dashboard/k.jpg"
                ,jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override

                    public void onResponse(JSONObject response) {
                        Log.d("response++++++", response.toString());
                        try {
                            //Toast.makeText(getApplicationContext(),response.toString(),Toast.LENGTH_SHORT).show();
                            Intent in =new Intent(login.this,image.class);
                            in.putExtra("Name",response.getString("name"));
                            in.putExtra("Password",response.getString("password"));
                            startActivity(in);
                            finish();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_SHORT).show();
                    }
                });


        *//*
   *//*     ImageRequest imageRequest=new ImageRequest("http://192.168.1.102/dashboard/k.jpg", new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {
            imv.setImageBitmap(response);
            }
        },
                0, 0, ImageView.ScaleType.CENTER_CROP, null,
                new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_SHORT).show();
            }
        });
        Toast.makeText(getApplicationContext(),"Comes In..",Toast.LENGTH_SHORT).show();
        requestQueue = (RequestQueue) Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(imageRequest);
   *//* }


    */@Override
    public void finish() {
        super.finish();
    }

}
