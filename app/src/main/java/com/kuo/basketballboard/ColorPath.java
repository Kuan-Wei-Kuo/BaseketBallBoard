package com.kuo.basketballboard;

import android.graphics.Path;

/**
 * Created by User on 2015/9/8.
 */
public class ColorPath {
    private Path path;
    private int color;

    public int getColor() {
        return color;
    }

    public Path getPath() {
        return path;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public void setPath(Path path) {
        this.path = path;
    }
}
