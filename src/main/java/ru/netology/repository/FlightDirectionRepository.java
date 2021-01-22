package ru.netology.repository;

import ru.netology.domain.FlightDirection;
import ru.netology.exception.NotFoundException;

public class FlightDirectionRepository {
    private FlightDirection[] flights = new FlightDirection[0];

    public FlightDirection findById(int id) {
        for (FlightDirection flight : flights) {
            if (flight.getId() == id) {
                return flight;
            }
        }
        return null;
    }

    public void removeById(int id) {
        if (findById(id) == null) {
            throw new NotFoundException("Element with id: " + id + " not found");
        }

        int length = flights.length - 1;
        FlightDirection[] tmp = new FlightDirection[length];
        int index = 0;
        for (FlightDirection flight : flights) {
            if (flight.getId() != id) {
                tmp[index] = flight;
                index++;
            }
        }
        flights = tmp;
    }


    public void save(FlightDirection item) {
        int length = flights.length + 1;
        FlightDirection[] tmp = new FlightDirection[length];
        System.arraycopy(flights, 0, tmp, 0, flights.length);
        int lastIndex = tmp.length - 1;
        tmp[lastIndex] = item;
        flights = tmp;
    }

    public FlightDirection[] findAll() {
        return flights;
    }
}
