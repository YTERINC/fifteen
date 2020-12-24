package com.example.pyatv1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
TextView mTextView;
    int[][] arr = new int[4][4];
    Random rnd = new Random();

int k;
int i;
int j;
int i1;
int j1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextView = findViewById(R.id.TextView);
        // mTextView.setText("test");
        //mTextView.setText(String.valueOf(b[3][3]));

        for (i = 0; i < 4; i++)
            for (j = 0; j < 4; j++) {
                arr[i][j] = 77;
            }

        for (i = 0; i < 4; i++)
            for (j = 0; j < 4; j++) {
                do {
                    k=0;
                    arr[i][j] = rnd.nextInt(16);
                    for (i1 = 0; i1 < 4; i1++)
                        for (j1 = 0; j1 < 4; j1++) {
                            if (arr[i][j] == arr[i1][j1])
                                k++;
                        }
                }
                while (k > 1);
            }

        for (i = 0; i < 4; i++) {
            for (j = 0; j < 4; j++)
                mTextView.append(arr[i][j] + "    ");
                mTextView.append("\n");
        }
    }

    public void onClick(View view) {

        Intent intent = new Intent(MainActivity.this, NewGame.class);
        startActivity(intent);


    }
}