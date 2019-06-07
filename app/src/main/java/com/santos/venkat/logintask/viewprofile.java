package com.santos.venkat.logintask;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class viewprofile extends AppCompatActivity {
    Button tVR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setContentView(R.layout.viewprofile);
       tVR=(Button)findViewById(R.id.edit);
        tVR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(viewprofile.this, edit.class);
                startActivity(i);
                finish();
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent in = new Intent(viewprofile.this, login.class);
                startActivity(in);
                this.finish();

                return true;
            case R.id.log_out:
                Intent intn = new Intent(viewprofile.this, header.class);
                startActivity(intn);
                this.finish();
                Toast.makeText(getApplicationContext(), "Log Out", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    private void getActivity() {
        Intent in = new Intent(viewprofile.this, login.class);
        startActivity(in);
        this.finish();

    }

    @Override
    public void onBackPressed() {
        getActivity();
        super.onBackPressed();
    }
}
