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
