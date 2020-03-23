package com.gayathriarumugam.intrasonics;

import android.app.Activity;

import org.junit.Test;

import static org.junit.Assert.*;

public class ExampleUnitTest {
    @Test
    public void isEven() {
        assertEquals(0, 2 % 2);
    }

    @Test
    public void isOdd() {
        assertFalse(3%2 == 0);
    }

    @Test
    public void ValidateSorting() {
        //Sorts and validate the sample input
        String numberString = "23, -21, 0, 8, 3";
        Integer[] sortedList = new Integer[5];
        sortedList[0] = -21;
        sortedList[1] = 0;
        sortedList[2] = 3;
        sortedList[3] = 8;
        sortedList[4] = 23;
        MainActivity mainActivity = new MainActivity();
        mainActivity.inputString = numberString;
        Integer[] list = mainActivity.numberSort();
        assertArrayEquals(sortedList, list);
    }
}