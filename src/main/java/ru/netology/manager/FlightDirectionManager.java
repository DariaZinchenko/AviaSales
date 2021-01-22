package ru.netology.manager;

import lombok.AllArgsConstructor;
import ru.netology.domain.FlightDirection;
import ru.netology.repository.FlightDirectionRepository;

import java.util.Arrays;

@AllArgsConstructor
public class FlightDirectionManager {

    private FlightDirectionRepository repository;

    public void add(FlightDirection product) {
        repository.save(product);
    }

    public FlightDirection[] findAll() {
        Arrays.sort(repository.findAll());
        return repository.findAll();
    }

    public FlightDirection[] findAll(String fromIATA, String toIATA) {
        FlightDirection[] result = new FlightDirection[0];
        FlightDirection[] flights = repository.findAll();

        for (FlightDirection flight : flights) {
            if (flight.matchesFrom(fromIATA) && flight.matchesTo(toIATA)) {
                int length = result.length + 1;
                FlightDirection[] tmp = new FlightDirection[length];

                System.arraycopy(result, 0, tmp, 0, result.length);

                tmp[length - 1] = flight;

                result = tmp;
            }
        }

        Arrays.sort(result);
        return result;
    }
}
