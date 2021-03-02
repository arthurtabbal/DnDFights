package com.example.dndfights;

public class Position {

    private int x = 0;
    private int y = 0;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void move(int dx, int dy) {
        this.x = x + dx;
        this.y = y + dy;
    }

    public void set(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
