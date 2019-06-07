package com.santos.venkat.logintask;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.wifi.hotspot2.pps.HomeSp;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class image extends AppCompatActivity {
  String name;
  String password;
  TextView tv1,tv2;
  ImageView img;
  Button cap;
  RequestQueue requestQueue;
    JSONObject jsonObject;
  private static final int REQUEST_IMAGE_CAPTURE=101;

  @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      View decorView = getWindow().getDecorView();
      int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
      decorView.setSystemUiVisibility(uiOptions);
      getSupportActionBar().setDisplayShowHomeEnabled(true);
      getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.image);




        // name=getIntent().getExtras().getString("Name");
       //  password=getIntent().getExtras().getString("Password");
//           tv1=(TextView)findViewById(R.id.tv1);
//           tv2=(TextView)findViewById(R.id.tv2);
             img=(ImageView)findViewById(R.id.imgV);
             cap=(Button)findViewById(R.id.Cap);
//           tv1.setText(name);
//           tv2.setText(password);
      cap.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              Intent Image = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
              if (Image.resolveActivity(getPackageManager()) != null) {
                  startActivityForResult(Image, REQUEST_IMAGE_CAPTURE);
                  //onSendServer();
              }
          }

         /* private void onSendServer() {
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

              JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                      Request.Method.POST, "http://192.168.1.102:8083/setuser"
                      ,jsonObject,
                      new Response.Listener<JSONObject>() {
                          @Override

                          public void onResponse(JSONObject response) {
                              Log.d("response++++++", response.toString());



                                  Toast.makeText(getApplicationContext(),response.toString(),Toast.LENGTH_SHORT).show();
                                  *//*Intent in =new Intent(image.this,image.class);
                                  in.putExtra("Name",response.getString("name"));
                                  in.putExtra("Password",response.getString("password"));
                                  startActivity(in);
                                  finish();
                              *//*

                          }
                      },
                      new Response.ErrorListener() {
                          @Override
                          public void onErrorResponse(VolleyError error)
                          {
                              Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_SHORT).show();
                          }
                      });
              requestQueue.add(jsonObjectRequest);

              ImageRequest imageRequest=new ImageRequest("http://192.168.1.102:8083/setuser", new Response.Listener<Bitmap>() {
                  @Override
                  public void onResponse(Bitmap response) {
                      //imv.setImageBitmap(response);

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
          }*/
      });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
      if(requestCode==REQUEST_IMAGE_CAPTURE&&resultCode==RESULT_OK)
      {
          Bundle extras=data.getExtras();
          Bitmap map= (Bitmap) extras.get("data");
          jsonObject = new JSONObject();
          try {
              jsonObject.put("image",map );
          }
          catch (Exception e){

          }
          img.setImageBitmap(map);
      }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent in = new Intent(image.this, login.class);
                startActivity(in);
                this.finish();

                return true;
            case R.id.view_profile:
                getActivity();
                /*firebaseAuthl=FirebaseAuth.getInstance();
                FirebaseUser user=firebaseAuthl.getCurrentUser();
                if(user!=null)
                {
                    user.delete();
                }*/
                //Toast.makeText(getApplicationContext(), "Log_Out", Toast.LENGTH_SHORT).show();

                //Intent in=new Intent(getApplicationContext(),OTP.class);
                //startActivity(in);
                return true;
            case R.id.log_out:
                Toast.makeText(getApplicationContext(), "Log Out", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    private void getActivity() {
        Intent in = new Intent(image.this, viewprofile.class);
        startActivity(in);
        this.finish();

    }

    @Override
    public void onBackPressed() {
        //getActivity();
        Intent in = new Intent(image.this, login.class);
        startActivity(in);
        this.finish();
        super.onBackPressed();
    }

}
