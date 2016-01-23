/*
 * Copyright 2015 Eduard Vodicka
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
