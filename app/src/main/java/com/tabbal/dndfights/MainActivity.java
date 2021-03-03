package com.tabbal.dndfights;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    public void openMyChars() {
        Intent intent = new Intent(this, MyCharsActivity.class);
        startActivity(intent);
    }

    public void openStartFight() {
        Intent intent = new Intent(this, StartFightActivity.class);
        startActivity(intent);
    }

    public void openDiceRoller() {
        Intent intent = new Intent(this, DiceTesterActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button diceRollerButton = findViewById(R.id.dice_tester);
        final Button myCharsButton = findViewById(R.id.my_chars);
        final Button startFightButton = findViewById(R.id.start_fight);

        diceRollerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDiceRoller();
            }
        });
        myCharsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMyChars();
            }
        });
        startFightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openStartFight();
            }
        });
    }
}