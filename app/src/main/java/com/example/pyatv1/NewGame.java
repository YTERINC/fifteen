package com.example.pyatv1;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Random;

public class NewGame extends AppCompatActivity{



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Graf2 graf2D = new Graf2(this);





        setContentView(graf2D);


    }




}