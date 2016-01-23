package de.eduardvodicka.applications.digitalframe.controller;

import org.springframework.stereotype.Component;

import java.time.LocalTime;

/**
 * Created by evodicka on 23.01.2016.
 */
@Component
public class TimeBasedResourceSelector implements ResourceSelector {

    @Override
    public int selectResourceId(int maxValue) {
        int duration = 60 / maxValue;
        int minute = LocalTime.now().getMinute();
        return minute / duration;
    }
}
