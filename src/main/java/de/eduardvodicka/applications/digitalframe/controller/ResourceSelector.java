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

/**
 * Provides an algorithm for selecting resources based on their id.
 *
 * @author Eduard Vodicka
 */
public interface ResourceSelector {

    /**
     * Provides an id that is greater than 0 and less or equals the passed value
     * @param maxValue max id value
     * @return selected id
     */
    int selectResourceId(int maxValue);

}
