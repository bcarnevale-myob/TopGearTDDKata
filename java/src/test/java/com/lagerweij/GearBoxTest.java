package com.lagerweij;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class GearBoxTest {

    @Test
    public void testSomething() {
        GearBox gearBox = new GearBox();
        gearBox.doit(7);

        assertTrue(true);
    }

    @Test
    public void previousRPMIsNextRPMAfterDoIt() {
        GearBox gearBox = new GearBox();
        gearBox.doit(1232);
        int firstRPM = gearBox.getPreviousRPM();
        gearBox.doit(12);
        int secondRPM = gearBox.getPreviousRPM();

        assertEquals(1232, firstRPM);
        assertEquals(12, secondRPM);
    }

    @Test
    public void changesToFirstGearAt500RPM() {
        GearBox gearBox = new GearBox();
        gearBox.doit(500);
        int gearNumber = gearBox.getGearNumber();

        assertEquals(1, gearNumber);
    }

    @Test
    public void changesToSecondGearAt2001RPM() {
        GearBox gearBox = new GearBox();
        gearBox.doit(500);
        gearBox.doit(2001);
        int gearNumber = gearBox.getGearNumber();

        assertEquals(2, gearNumber);
    }

    @Test
    public void changesBackToFirstGearAt499RPM() {
        GearBox gearBox = new GearBox();
        gearBox.doit(500);
        gearBox.doit(2001);
        gearBox.doit(499);
        int gearNumber = gearBox.getGearNumber();

        assertEquals(1, gearNumber);
    }


}
