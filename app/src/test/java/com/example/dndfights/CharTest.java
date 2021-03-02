package com.example.dndfights;

import junit.framework.TestCase;

import org.junit.Assert;

public class CharTest extends TestCase {

    public void testGetModifier() {

        for (int i = 1; i <= 20; i++) {
            System.out.println("Atributo : " + i + "  Modificador : " + Char.getModifier(i));
        }


    }
}