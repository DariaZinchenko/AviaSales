package ru.netology.comparator;

import ru.netology.domain.FlightDirection;

import java.util.Comparator;

public class FlightDirectionByPriceAscComparator implements Comparator<FlightDirection> {

    public int compare(FlightDirection o1, FlightDirection o2) {
        return (int) (o1.getCostInRubles() - o2.getCostInRubles());
    }
}
