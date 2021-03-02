package com.example.dndfights;

public class MonsterChar extends Char {
    public MonsterChar(String name) {
        super(name);
        this.setAsEnemy();
    }
}
