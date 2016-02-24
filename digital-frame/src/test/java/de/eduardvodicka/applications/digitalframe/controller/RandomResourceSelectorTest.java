package de.eduardvodicka.applications.digitalframe.controller;

import org.junit.Test;

import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertThat;

/**
 * Created by evodicka on 23.01.2016.
 */
public class RandomResourceSelectorTest {

    private ResourceSelector underTest = new RandomResourceSelector();

    @Test(expected = IllegalArgumentException.class)
    public void select_exception() {
        int result = underTest.selectResourceId(0);
    }

    @Test
    public void select_valid() {
        int result = underTest.selectResourceId(20);

        assertThat(result, greaterThanOrEqualTo(0));
        assertThat(result, lessThan(21));
    }
}