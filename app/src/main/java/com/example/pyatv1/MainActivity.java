package com.example.pyatv1;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        MainGameGraphics mainGameGraphics = new MainGameGraphics(this);
        setContentView(mainGameGraphics);
    }




}