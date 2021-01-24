package ru.netology.comparator;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import ru.netology.domain.FlightDirection;

import static org.junit.jupiter.api.Assertions.*;

class FlightDirectionByPriceAscComparatorTest {

    @ParameterizedTest(name = "{index} {0}")
    @CsvSource({
      "compare first Object over second Object, 36670, -1",
      "compare first Object equals second Object, 36669, 0",
      "compare first Object under second Object, 36668, 1"})
    public void compareTest(String testName, long cost, int expected) {
        FlightDirection first = new FlightDirection(1, 36669, "LED", "OVB", 260);
        FlightDirection second = new FlightDirection(2, cost, "LED", "OVB", 260);

        FlightDirectionByPriceAscComparator comparator = new FlightDirectionByPriceAscComparator();

        int actual = comparator.compare(first, second);

        assertEquals(expected, actual);
    }
}