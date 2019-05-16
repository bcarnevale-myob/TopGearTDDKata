package com.lagerweij;

public class Gear {

    public Gear(int lowestRPM, int highestRPM) {
        this.lowestRPM = lowestRPM;
        this.highestRPM = highestRPM;
    }

    public int getLowestRPM() {
        return lowestRPM;
    }

    public int getHighestRPM() {
        return highestRPM;
    }

    int lowestRPM;
    int highestRPM;
}
