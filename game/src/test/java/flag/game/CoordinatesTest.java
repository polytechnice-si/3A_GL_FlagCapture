package flag.game;


import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class CoordinatesTest {

    private static final double PRECISION = 0.0001;

    @Test
    public void shouldComputeDistance() {
        Coordinates origin = new Coordinates(0,0);
        assertEquals(0.0, origin.distance(origin), PRECISION);

        Coordinates p1 = new Coordinates(2, -1);
        Coordinates p2 = new Coordinates(-2, 2);
        assertEquals(5.0, p1.distance(p2), PRECISION);

        assertEquals(p1.distance(p2), p2.distance(p1), PRECISION);
    }

}