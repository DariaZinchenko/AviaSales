package ru.netology.manager;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.netology.comparator.FlightDirectionByPriceAscComparator;
import ru.netology.domain.FlightDirection;
import ru.netology.repository.FlightDirectionRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class FlightDirectionManagerNonEmptyTest {

    @Mock
    private FlightDirectionRepository repository;
    @InjectMocks
    private FlightDirectionManager manager;

    private FlightDirection first = new FlightDirection(1, 36669, "LED", "OVB", 260);
    private FlightDirection second = new FlightDirection(2, 34934, "SVO", "ICN", 520);
    private FlightDirection third = new FlightDirection(3,  2099, "LED", "OVB", 260);
    private FlightDirection fourth = new FlightDirection(4, 29681, "LED", "OVB", 260);
    private FlightDirection fifth = new FlightDirection(5, 23744, "SVO", "HND", 585);

    public void setUp() {
        repository.save(first);
        repository.save(second);
        repository.save(third);
        repository.save(fourth);
        repository.save(fifth);
    }

    @Test
    public void findAllNoArgsTest() {
        repository = new FlightDirectionRepository();
        setUp();
        manager = new FlightDirectionManager(repository);

        FlightDirection[] actual = manager.findAll();
        FlightDirection[] expected = new FlightDirection[] {third, fifth, fourth, second, first};

        assertArrayEquals(expected, actual);
    }

    @Test
    public void findAllSeveralElementResultTest() {
        FlightDirection[] returned = new FlightDirection[] {first, second, third, fourth, fifth};
        doReturn(returned).when(repository).findAll();

        manager = new FlightDirectionManager(repository);

        FlightDirection[] actual = manager.findAll("LED", "OVB");
        FlightDirection[] expected = new FlightDirection[] {third, fourth, first};

        assertArrayEquals(expected, actual);
    }

    @Test
    public void findAllOneElementResultTest() {
        FlightDirection[] returned = new FlightDirection[] {first, second, third, fourth, fifth};
        doReturn(returned).when(repository).findAll();

        manager = new FlightDirectionManager(repository);

        FlightDirection[] actual = manager.findAll("SVO", "ICN");
        FlightDirection[] expected = new FlightDirection[] {second};

        assertArrayEquals(expected, actual);
    }

    @Test
    public void findAllEmptyResultTest() {
        FlightDirection[] returned = new FlightDirection[] {first, second, third, fourth, fifth};
        doReturn(returned).when(repository).findAll();

        manager = new FlightDirectionManager(repository);

        FlightDirection[] actual = manager.findAll("SVO", "ICN_1");
        FlightDirection[] expected = new FlightDirection[0];

        assertArrayEquals(expected, actual);
    }

    @Test
    public void findAllComporatorSeveralElementResultTest() {
        FlightDirectionByPriceAscComparator comparator = new FlightDirectionByPriceAscComparator();
        FlightDirection[] returned = new FlightDirection[] {first, second, third, fourth, fifth};
        doReturn(returned).when(repository).findAll();

        manager = new FlightDirectionManager(repository);

        FlightDirection[] actual = manager.findAll("LED", "OVB", comparator);
        FlightDirection[] expected = new FlightDirection[] {third, fourth, first};

        assertArrayEquals(expected, actual);
    }

    @Test
    public void findAllComporatorOneElementResultTest() {
        FlightDirectionByPriceAscComparator comparator = new FlightDirectionByPriceAscComparator();
        FlightDirection[] returned = new FlightDirection[] {first, second, third, fourth, fifth};
        doReturn(returned).when(repository).findAll();

        manager = new FlightDirectionManager(repository);

        FlightDirection[] actual = manager.findAll("SVO", "ICN", comparator);
        FlightDirection[] expected = new FlightDirection[] {second};

        assertArrayEquals(expected, actual);
    }

    @Test
    public void findAllComporatorEmptyResultTest() {
        FlightDirectionByPriceAscComparator comparator = new FlightDirectionByPriceAscComparator();
        FlightDirection[] returned = new FlightDirection[] {first, second, third, fourth, fifth};
        doReturn(returned).when(repository).findAll();

        manager = new FlightDirectionManager(repository);

        FlightDirection[] actual = manager.findAll("SVO", "ICN_1", comparator);
        FlightDirection[] expected = new FlightDirection[0];

        assertArrayEquals(expected, actual);
    }

    @Test
    public void addNonEmptyRepoTest() {
        FlightDirection sixth = new FlightDirection(6, 23669, "LED", "ICN", 460);
        repository = new FlightDirectionRepository();
        setUp();
        manager = new FlightDirectionManager(repository);

        manager.add(sixth);

        FlightDirection[] actual = manager.findAll();
        FlightDirection[] expected = new FlightDirection[] {third, sixth, fifth, fourth, second, first};

        assertArrayEquals(expected, actual);
    }
}