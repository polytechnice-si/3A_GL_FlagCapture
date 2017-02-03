package flag.game;


import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static flag.game.Compass.Heading.*;

public class CompassTest {

    private Compass compass;

    @Before public void initCompass() { compass = new Compass(EAST); }

    @Test
    public void shouldTurn() {
        compass.turnLeft();
        assertNotEquals(EAST, compass.getHeading());
    }

    @Test
    public void shouldTurnAndGoBack() {
        compass.turnLeft();
        compass.turnRight();
        assertEquals(EAST, compass.getHeading());
    }

    @Test
    public void shouldTurnLeft() {
        compass.turnLeft();
        assertEquals(NORTH, compass.getHeading());
        compass.turnLeft();
        assertEquals(WEST, compass.getHeading());
        compass.turnLeft();
        assertEquals(SOUTH, compass.getHeading());
        compass.turnLeft();
        assertEquals(EAST, compass.getHeading());
    }

    @Test
    public void shouldTurnRight() {
        compass.turnRight();
        assertEquals(SOUTH, compass.getHeading());
        compass.turnRight();
        assertEquals(WEST, compass.getHeading());
        compass.turnRight();
        assertEquals(NORTH, compass.getHeading());
        compass.turnRight();
        assertEquals(EAST, compass.getHeading());
    }

    @Test
    public void shouldMoveAccurately() {
        Coordinates coords = new Coordinates(0,0);
        assertEquals(new Coordinates(-1,  0), (new Compass(WEST)).nextPosition(coords));
        assertEquals(new Coordinates( 1,  0), (new Compass(EAST)).nextPosition(coords));
        assertEquals(new Coordinates( 0, -1), (new Compass(NORTH)).nextPosition(coords));
        assertEquals(new Coordinates( 0,  1), (new Compass(SOUTH)).nextPosition(coords));
    }

}
