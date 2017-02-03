package polytech.qgl.phases;


import flag.game.*;
import org.junit.*;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class GoRightTest {

    private Phase phase;
    private GameMap map;

    @Before public void init() {
        this.phase = new GoRight();
        phase.setPlayer(mock(Player.class, CALLS_REAL_METHODS));
        map = new GameMap(10, 10, new Coordinates(5,5));
        phase.setHeight(map.getHeight());
        phase.setWidth(map.getWidth());
    }

    @Test
    public void shouldGoRightUntilTheMapBoundary() {
        assertEquals(0, phase.getX()); assertEquals(0, phase.getY());
        phase.play(new Game(map));
        assertEquals(9, phase.getX()); assertEquals(0, phase.getY());
    }

}