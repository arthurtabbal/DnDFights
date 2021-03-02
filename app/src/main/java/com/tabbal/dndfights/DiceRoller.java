package com.tabbal.dndfights;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

abstract public class DiceRoller extends AppCompatActivity {

    static Random rand = new Random();

    public DiceRoller() {
    //    Log.d("initD","Initializing DiceRoller...");
        int initFaces = 20;
        int nextRoll = rand.nextInt(initFaces);
    //    Log.d("roll","Test roll = " + nextRoll);
    }

    static public int roll(int faces) {
        return rand.nextInt(faces) + 1;
    }

    static public int rollD100() {
        return roll(100);
    }
    static public int rollD20() {
        return roll(20);
    }
    static public int rollD12() {
        return roll(12);
    }
    static public int rollD10() {
        return roll(10);
    }
    static public int rollD8() {
        return roll(8);
    }
    static public int rollD6() {
        return roll(6);
    }
    static public int rollD4() {
        return roll(4);
    }

    static public int rollAttrStd() {
        return (rollD6() + rollD6() + rollD6()); }
}
