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

/**
 * Selects a resourcce id on random basis
 *
 * @author Eduard Vodicka
 */
@Component("randomSelector")
public class RandomResourceSelector implements ResourceSelector {

    @Override
    public int selectResourceId(int maxValue) {
        if(maxValue <= 0) {
            throw new IllegalArgumentException("Max value must be greater then 0");
        }
        return (int) ((Math.random() * 100)) % maxValue;
    }
}
