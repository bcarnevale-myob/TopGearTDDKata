package com.lagerweij;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class GearBoxTest {

    public static final int NEUTRAL_GEAR = 0;

    @Test
    public void determineGearFromUpdatesLastReceivedRPM() {
        GearBox gearBox = new GearBox();
        gearBox.determineGearFrom(1232);
        int firstRPM = gearBox.getLastReceivedRPM();
        gearBox.determineGearFrom(12);
        int secondRPM = gearBox.getLastReceivedRPM();

        assertEquals(1232, firstRPM);
        assertEquals(12, secondRPM);
    }

    @Test
    public void gearboxStartsAtNeutralGear() {
        GearBox gearBox = new GearBox();
        int actualGear = gearBox.getCurrentGear();

        assertEquals(NEUTRAL_GEAR, actualGear);
    }

    @Test
    public void changesToFirstGearAt500RPM() {
        GearBox gearBox = new GearBox();
        gearBox.determineGearFrom(500);
        int actualGear = gearBox.getCurrentGear();

        assertEquals(1, actualGear);
    }

    @Test
    public void fromNeutralTo2001RPM() {
        GearBox gearBox = new GearBox();

        gearBox.determineGearFrom(2001);
        int actualGear = gearBox.getCurrentGear();

        assertEquals(1, actualGear);
    }

    @Test
    public void doesNotChangeToSecondGearAt2000RPM(){
        GearBox gearBox = createGearBoxInFirstGear();
        gearBox.determineGearFrom(2000);
        int actualGear = gearBox.getCurrentGear();

        assertEquals(1, actualGear);
    }

    @Test
    public void changesToSecondGearAt2001RPM() {
        GearBox gearBox = createGearBoxInFirstGear();
        gearBox.determineGearFrom(2001);
        int actualGear = gearBox.getCurrentGear();

        assertEquals(2, actualGear);
    }

    @Test
    public void doesNotChangeDownAt500RPM(){
        GearBox gearBox = createGearBoxInFirstGear();
        gearBox.determineGearFrom(2001);
        gearBox.determineGearFrom(500);
        int actualGear = gearBox.getCurrentGear();

        assertEquals(2, actualGear);
    }

    @Test
    public void changesBackToFirstGearAt499RPM() {
        GearBox gearBox = createGearBoxInFirstGear();
        gearBox.determineGearFrom(2001);
        gearBox.determineGearFrom(499);
        int actualGear = gearBox.getCurrentGear();

        assertEquals(1, actualGear);
    }

    @Test
    public void inFirstGearAndGivenZeroRPMStaysInFirst() {
        GearBox gearBox = createGearBoxInFirstGear();
        gearBox.determineGearFrom(0);
        int actualGear = gearBox.getCurrentGear();

        assertEquals(1, actualGear);
    }

    @Test
    public void hasSixGears() {
        GearBox gearBox = createGearBoxInFirstGear();
        shiftUpGears(gearBox, 5);
        int actualGear = gearBox.getCurrentGear();
        
        assertEquals(6, actualGear);
    }
    @Test
    public void changesDownOneGearAtATime() {
        GearBox gearBox = createGearBoxInFirstGear();

        shiftUpGears(gearBox, 5);

        gearBox.determineGearFrom(499);
        gearBox.determineGearFrom(499);
        int actualGear = gearBox.getCurrentGear();

        assertEquals(4, actualGear);
    }

    @Test
    public void doesNotShiftPastSixthGear() {
        GearBox gearBox = createGearBoxInFirstGear();
        shiftUpGears(gearBox, 7);
        int actualGear = gearBox.getCurrentGear();

        assertEquals(6, actualGear);
    }

    private GearBox createGearBoxInFirstGear() {
        GearBox gearBox = new GearBox();
        gearBox.determineGearFrom(500);
        return gearBox;
    }

    private void shiftUpGears(GearBox gearBox, int gears) {
        for (int i = 0; i < gears ; i++) {
            gearBox.determineGearFrom(2001);
        }
    }
}
