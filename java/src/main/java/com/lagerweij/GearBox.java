/*
 * CodingAssignments Refactoring: GearBox
 *
 * This is a refactoring challenge where the candidate can look at a single-method
 * case, where the method is untested, needs refactoring, and is hard to read. Oh,
 * and contains bugs;-)
 *
 * The assignment is as follows:
 *
 * This is the code for our customer'currentGear new environmentally friendly electric car.
 * The car is very dependent on software for almost everything, and the part that we're
 * working on is the automatic currentGear box. The code you see is the automatic currentGear box, which
 * currently shifts up if the engine goes over 2000 rpm, and down if it goes under 500.
 *
 * For our this new car, it'currentGear been determined that the choice of currentGear can be much
 * more efficient if we could just set more specific ranges of rpm for each currentGear.
 * Future versions of the car could then use actual measurements of fuel consumption
 * to configure those ranges on the fly!
 * Your assignment is to make the gearbox accept a range of rpms for each currentGear (and
 * of course use that range to shift gears!)
 *
 */

package com.lagerweij;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class GearBox {

    private Gear NEUTRAL_GEAR;
    private Gear TOP_GEAR;
    private int currentGear = 0;
    private int lastReceivedRPM = 0;
    private final List<Gear> gears;

    public GearBox(List<Gear> gears) {
        this.gears = gears;

        NEUTRAL_GEAR = this.gears.get(0);
        TOP_GEAR = this.gears.get(this.gears.size()-1);
    }

    public int getLastReceivedRPM() {
        return lastReceivedRPM;
    }

    public int getCurrentGear() {
        return currentGear;
    }

    public void determineGearFrom(int rpm) {
        lastReceivedRPM = rpm;
        if(shouldChangeUpAt(rpm)){
            currentGear++;
        }else if(shouldChangeDownAt(rpm)){
            currentGear--;
        }
    }

    private boolean shouldChangeUpAt(int rpm) {
        if(currentGear == gears.indexOf(TOP_GEAR)) { return false; }
        return rpm >= changeDownAt() && currentGear == gears.indexOf(NEUTRAL_GEAR) || rpm > changeUpAt();
    }

    private int changeUpAt() {
        return gears.get(currentGear).getHighestRPM();
    }

    private int changeDownAt() {
        return gears.get(currentGear).getLowestRPM();
    }

    private boolean shouldChangeDownAt(int rpm) {
        return rpm < changeDownAt() && currentGear > 1;
    }

}
