package com.example.dndfights;

public class Grid {

    final private int sizeWidth, sizeHeight;
    final private boolean has_walls;
    Fight fight;

    public Grid(int sizeWidth, int sizeHeight, boolean has_walls) {
        this.sizeHeight = sizeHeight;
        this.sizeWidth = sizeWidth;
        this.has_walls = has_walls;
    }

    public boolean hasWalls() { return has_walls; }

    public int getSizeWidth() { return sizeWidth;  }

    public int getSizeHeight() { return sizeHeight;}
}
