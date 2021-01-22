package ru.netology.domain;

import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Data;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class FlightDirection implements Comparable<FlightDirection>{
    private int id;
    private long costInRubles;
    private String fromIATA;
    private String toIATA;
    private int travelTimeInMinutes;

    @Override
    public int compareTo(FlightDirection o) {
        return (int) (costInRubles - o.costInRubles);
    }

    public boolean matchesFrom(String search) {
        return fromIATA.equalsIgnoreCase(search);
    }

    public boolean matchesTo(String search) {
        return toIATA.equalsIgnoreCase(search);
    }
}
