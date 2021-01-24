package ru.netology.manager;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.netology.comparator.FlightDirectionByPriceAscComparator;
import ru.netology.domain.FlightDirection;
import ru.netology.repository.FlightDirectionRepository;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class FlightDirectionManagerEmptyTest {

    @Mock
    private FlightDirectionRepository repository;
    @InjectMocks
    private FlightDirectionManager manager;

    @Test
    public void findAllNoArgsEmptyRepoTest() {
        repository = new FlightDirectionRepository();
        manager = new FlightDirectionManager(repository);

        FlightDirection[] actual = manager.findAll();
        FlightDirection[] expected = new FlightDirection[0];

        assertArrayEquals(expected, actual);
    }

    @Test
    public void findAllNoArgsNULLRepoTest() {
        manager = new FlightDirectionManager(repository);

        assertThrows(NullPointerException.class, () -> manager.findAll());
    }

    @Test
    public void findAllEmptyRepoTest() {
        repository = new FlightDirectionRepository();
        manager = new FlightDirectionManager(repository);

        FlightDirection[] actual = manager.findAll("SVO", "ICN");
        FlightDirection[] expected = new FlightDirection[0];

        assertArrayEquals(expected, actual);
    }

    @Test
    public void findAllNULLRepoTest() {
        manager = new FlightDirectionManager(repository);

        assertThrows(NullPointerException.class, () -> manager.findAll("SVO", "ICN"));
    }

    @Test
    public void findAllComporatorEmptyRepoTest() {
        FlightDirectionByPriceAscComparator comparator = new FlightDirectionByPriceAscComparator();
        repository = new FlightDirectionRepository();
        manager = new FlightDirectionManager(repository);

        FlightDirection[] actual = manager.findAll("SVO", "ICN", comparator);
        FlightDirection[] expected = new FlightDirection[0];

        assertArrayEquals(expected, actual);
    }

    @Test
    public void findAllComporatorNULLRepoTest() {
        FlightDirectionByPriceAscComparator comparator = new FlightDirectionByPriceAscComparator();
        manager = new FlightDirectionManager(repository);

        assertThrows(NullPointerException.class, () -> manager.findAll("SVO", "ICN", comparator));
    }

    @Test
    public void addEmptyRepoTest() {
        repository = new FlightDirectionRepository();
        manager = new FlightDirectionManager(repository);

        FlightDirection sixth = new FlightDirection(6, 23669, "LED", "ICN", 460);
        manager = new FlightDirectionManager(repository);

        manager.add(sixth);

        FlightDirection[] actual = manager.findAll();
        FlightDirection[] expected = new FlightDirection[] {sixth};

        assertArrayEquals(expected, actual);
    }
}