package ru.netology.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class FlightDirectionTest {

    @ParameterizedTest(name = "{index} {0}")
    @CsvSource({
      "compareTo less, 36670, -1",
      "compareTo equals, 36669, 0",
      "compareTo more, 36668, 1",})
    public void compareToTest(String testName, long cost, int expected) {
        FlightDirection first = new FlightDirection(1, 36669, "LED", "OVB", 260);
        FlightDirection second = new FlightDirection(2, cost, "LED", "OVB", 260);

        int actual = first.compareTo(second);

        assertEquals(expected, actual);
    }

    @ParameterizedTest(name = "{index} {0}")
    @CsvSource({
      "matchesFrom true, LED, true",
      "matchesFrom false, LED_1, false"})
    public void matchesFromTest(String testName, String text, boolean expected) {
        FlightDirection flightDirection = new FlightDirection(1, 36669, "LED", "OVB", 260);
        boolean actual = flightDirection.matchesFrom(text);

        assertEquals(expected, actual);
    }

    @ParameterizedTest(name = "{index} {0}")
    @CsvSource({
      "matchesTo true, OVB, true",
      "matchesTo false, OVB_1, false"})
    public void matchesToTest(String testName, String text, boolean expected) {
        FlightDirection flightDirection = new FlightDirection(1, 36669, "LED", "OVB", 260);
        boolean actual = flightDirection.matchesTo(text);

        assertEquals(expected, actual);
    }
}