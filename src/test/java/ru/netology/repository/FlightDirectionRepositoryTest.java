package ru.netology.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.FlightDirection;
import ru.netology.exception.NotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class FlightDirectionRepositoryTest {
    private FlightDirectionRepository repository = new FlightDirectionRepository();

    private FlightDirection first = new FlightDirection(1, 36669, "LED", "OVB", 260);
    private FlightDirection second = new FlightDirection(2, 34934, "SVO", "ICN", 520);
    private FlightDirection third = new FlightDirection(3,  2099, "LED", "OVB", 260);
    private FlightDirection fourth = new FlightDirection(4, 29681, "LED", "OVB", 260);
    private FlightDirection fifth = new FlightDirection(5, 23744, "SVO", "HND", 585);

    @BeforeEach
    public void setUp() {
        repository.save(first);
        repository.save(second);
        repository.save(third);
        repository.save(fourth);
        repository.save(fifth);
    }

    @Test
    void removeByIdElementExistTest() {
        repository.removeById(3);
        FlightDirection[] expected = new FlightDirection[]{first, second, fourth, fifth};
        FlightDirection[] actual = repository.findAll();

        assertArrayEquals(expected, actual);
    }

    @Test
    public void removeByIdElementNotExistTest() {

        assertThrows(NotFoundException.class, () -> repository.removeById(50));
    }

    @Test
    void saveEmptyArrayTest() {
        FlightDirectionRepository repository = new FlightDirectionRepository();
        FlightDirection sixth = new FlightDirection(6, 23669, "LED", "ICN", 460);
        repository.save(sixth);

        FlightDirection[] expected = new FlightDirection[]{sixth};
        FlightDirection[] actual = repository.findAll();

        assertArrayEquals(expected, actual);
    }

    @Test
    void saveNonEmptyArrayTest() {
        FlightDirection sixth = new FlightDirection(6, 23669, "LED", "ICN", 460);
        repository.save(sixth);

        FlightDirection[] expected = new FlightDirection[]{first, second, third, fourth, fifth, sixth};
        FlightDirection[] actual = repository.findAll();

        assertArrayEquals(expected, actual);
    }

    @Test
    void findAllEmptyArrayTest() {
        FlightDirectionRepository repository = new FlightDirectionRepository();
        FlightDirection[] expected = new FlightDirection[0];
        FlightDirection[] actual = repository.findAll();

        assertArrayEquals(expected, actual);
    }

    @Test
    void findAllNonEmptyArrayTest() {
        FlightDirection[] expected = new FlightDirection[]{first, second, third, fourth, fifth};
        FlightDirection[] actual = repository.findAll();

        assertArrayEquals(expected, actual);
    }
}