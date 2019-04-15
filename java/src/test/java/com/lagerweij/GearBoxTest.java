package com.lagerweij;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class GearBoxTest {

    public static final int NEUTRAL_GEAR = 0;

    @Test
    public void doItUpdatesPreviousRPM() {
        GearBox gearBox = new GearBox();
        gearBox.doit(1232);
        int firstRPM = gearBox.getPreviousRPM();
        gearBox.doit(12);
        int secondRPM = gearBox.getPreviousRPM();

        assertEquals(1232, firstRPM);
        assertEquals(12, secondRPM);
    }

    @Test
    public void gearboxStartsAtZeroRPM() {
        GearBox gearBox = new GearBox();
        int actualGear = gearBox.getGearNumber();

        assertEquals(NEUTRAL_GEAR, actualGear);

    }

    @Test
    public void changesToFirstGearAt500RPM() {
        GearBox gearBox = new GearBox();
        gearBox.doit(500);
        int actualGear = gearBox.getGearNumber();

        assertEquals(1, actualGear);
    }

    @Test
    public void doesNotChangeToSecondGearAt2000RPM(){
        GearBox gearBox = new GearBox();
        gearBox.doit(500);
        gearBox.doit(2000);
        int actualGear = gearBox.getGearNumber();

        assertEquals(1, actualGear);
    }

    @Test
    public void changesToSecondGearAt2001RPM() {
        GearBox gearBox = new GearBox();
        gearBox.doit(500);
        gearBox.doit(2001);
        int actualGear = gearBox.getGearNumber();

        assertEquals(2, actualGear);
    }

    @Test
    public void doesNotChangeDownAt500RPM(){
        GearBox gearBox = new GearBox();
        gearBox.doit(500);
        gearBox.doit(2001);
        gearBox.doit(500);
        int actualGear = gearBox.getGearNumber();

        assertEquals(2, actualGear);
    }

    @Test
    public void changesBackToFirstGearAt499RPM() {
        GearBox gearBox = new GearBox();
        gearBox.doit(500);
        gearBox.doit(2001);
        gearBox.doit(499);
        int actualGear = gearBox.getGearNumber();

        assertEquals(1, actualGear);
    }

    @Test
    public void inFirstGearAndGivenZeroRPMStaysInFirst() {
        GearBox gearBox = new GearBox();
        gearBox.doit(500);
        gearBox.doit(0);
        int actualGear = gearBox.getGearNumber();

        assertEquals(1, actualGear);
    }

    @Test
    public void hasSixGears() {
        GearBox gearBox = new GearBox();
        gearBox.doit(500);
        gearBox.doit(2001);
        gearBox.doit(2001);
        gearBox.doit(2001);
        gearBox.doit(2001);
        gearBox.doit(2001);
        int actualGear = gearBox.getGearNumber();
        
        assertEquals(6, actualGear);
    }
    @Test
    public void changesDownOneGearAtATime() {
        GearBox gearBox = new GearBox();
        gearBox.doit(500);
        gearBox.doit(2001);
        gearBox.doit(2001);
        gearBox.doit(2001);
        gearBox.doit(2001);
        gearBox.doit(2001);
        gearBox.doit(499);
        gearBox.doit(499);
        int actualGear = gearBox.getGearNumber();

        assertEquals(4, actualGear);
    }

    @Test
    public void sixthGearIsTopGear() {
        GearBox gearBox = new GearBox();
        gearBox.doit(500);
        gearBox.doit(2001);
        gearBox.doit(2001);
        gearBox.doit(2001);
        gearBox.doit(2001);
        gearBox.doit(2001);
        gearBox.doit(2001);
        gearBox.doit(2001);
        gearBox.doit(2001);
        gearBox.doit(2001);
        int actualGear = gearBox.getGearNumber();

        assertEquals(6, actualGear);
    }
}
