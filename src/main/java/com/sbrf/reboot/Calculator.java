package com.sbrf.reboot;

public class Calculator {
    public int getAddition(int x, int y) {
        return x + y;
    }

    public int getSubtraction(int x, int y) {
        return x - y;
    }

    public int getMultiplication(int x, int y) {
        return x * y;
    }

    public int getDivision(int x, int y){
        return x/y;
    }

    public int getMinValue(int x, int y){
        return Math.min(x, y);
    }

    public int getAbsoluteValue(int x){
        return Math.abs(x);
    }

    public int getMaxValue(int x, int y){
        return Math.max(x, y);
    }
}
