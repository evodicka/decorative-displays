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
