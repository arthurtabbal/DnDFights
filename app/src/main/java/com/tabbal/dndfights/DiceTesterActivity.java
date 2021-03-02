package com.tabbal.dndfights;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DiceTesterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int initRoll = DiceRoller.rollD20();
        Log.d("rollMain","Roll from main : " + String.valueOf(initRoll));

        setContentView(R.layout.activity_dice_tester);

        final Button buttonD100 = findViewById(R.id.button_d100);
        final Button buttonD20 = findViewById(R.id.button_d20);
        final Button buttonD12 = findViewById(R.id.button_d12);
        final Button buttonD10 = findViewById(R.id.button_d10);
        final Button buttonD4 = findViewById(R.id.button_d4);
        final Button buttonD6 = findViewById(R.id.button_d6);
        final Button buttonD8 = findViewById(R.id.button_d8);
        final TextView currentRoll = findViewById(R.id.current_roll);

       buttonD20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int nextRoll = DiceRoller.rollD20();
                Log.d("rollButton", "Button Roll ==> " + nextRoll);
                currentRoll.setText(String.valueOf(nextRoll));
            }
        });
       buttonD8.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               int nextRoll = DiceRoller.rollD8();
               Log.d("rollButton", "Button Roll ==> " + nextRoll);
               currentRoll.setText(String.valueOf(nextRoll));

           }
       });
        buttonD10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int nextRoll = DiceRoller.rollD10();
                Log.d("rollButton", "Button Roll ==> " + nextRoll);
                currentRoll.setText(String.valueOf(nextRoll));


            }
        });
        buttonD4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int nextRoll = DiceRoller.rollD4();
                Log.d("rollButton", "Button Roll ==> " + nextRoll);
                currentRoll.setText(String.valueOf(nextRoll));
            }
        });
        buttonD6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int nextRoll = DiceRoller.rollD6();
                Log.d("rollButton", "Button Roll ==> " + nextRoll);
                currentRoll.setText(String.valueOf(nextRoll));
            }
        });
        buttonD12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int nextRoll = DiceRoller.rollD12();
                Log.d("rollButton", "Button Roll ==> " + nextRoll);
                currentRoll.setText(String.valueOf(nextRoll));
            }
        });
        buttonD100.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int nextRoll = DiceRoller.rollD100();
                Log.d("rollButton", "Button Roll ==> " + nextRoll);
                currentRoll.setText(String.valueOf(nextRoll));
            }
        });
    }
}
