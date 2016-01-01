package com.infosouq;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.infosouq.Util.Actions;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        if(new Actions(this).loginStatus()){

        }else {
            Intent login = new Intent(this,Login_Register.class);
            startActivity(login);
            finish();
        }

    }
}
