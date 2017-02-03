package polytech.qgl.phases;


import flag.game.Coordinates;
import flag.game.Game;
import flag.game.GameMap;
import flag.game.Player;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.CALLS_REAL_METHODS;
import static org.mockito.Mockito.mock;

public class GoLeftTest {

    private Phase phase;
    private GameMap map;

    @Before public void init() {
        this.phase = new GoLeft();
        phase.setPlayer(mock(Player.class, CALLS_REAL_METHODS));
        map = new GameMap(10, 10, new Coordinates(5,5));
        phase.setHeight(map.getHeight());
        phase.setWidth(map.getWidth());
        phase.setX(9);
    }

    @Test
    public void shouldGoLeftUntilTheMapBoundary() {
        assertEquals(9, phase.getX()); assertEquals(0, phase.getY());
        phase.play(new Game(map));
        assertEquals(0, phase.getX()); assertEquals(0, phase.getY());
    }

}