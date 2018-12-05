package com.example.akgaynor.activity04;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onGameStartClick(View v){
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }

    public void onQuickClick(View v){
        finish();
    }
}
