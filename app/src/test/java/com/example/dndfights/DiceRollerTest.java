package com.example.dndfights;

import junit.framework.TestCase;

import org.junit.Assert;

public class DiceRollerTest extends TestCase {

    public void testRollD() {
        int[] rolls_d10 = new int[100];
        int faces = 20;
        for (int i = 0; i < 100; i++) {
            rolls_d10[i] = DiceRoller.roll(faces);
            System.out.println(rolls_d10[i]);
            Assert.assertTrue(rolls_d10[i] >=1 & rolls_d10[i] <= 10);
        }
    }
}