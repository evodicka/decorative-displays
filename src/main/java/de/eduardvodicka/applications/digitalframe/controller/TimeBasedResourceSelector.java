package de.eduardvodicka.applications.digitalframe.controller;

import org.springframework.stereotype.Component;

import java.time.LocalTime;

/**
 * Resource selector that is based on the current time. Ensures that all ids are distributed equally during and hour.
 * Every id gets its own timeslot and does not change during that time
 *
 * @author
 */
@Component("timeBasedSelector")
public class TimeBasedResourceSelector implements ResourceSelector {

    @Override
    public int selectResourceId(int maxValue) {
        if(maxValue <= 0) {
            throw new IllegalArgumentException("Max value must be greater then 0");
        }
        int duration = 60 / maxValue;
        int minute = LocalTime.now().getMinute();
        return minute / duration;
    }
}
