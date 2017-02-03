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

public class TurnLeftTest {

    private Phase phase;
    private GameMap map;

    @Before public void init() {
        this.phase = new TurnLeft();
        phase.setPlayer(mock(Player.class, CALLS_REAL_METHODS));
        map = new GameMap(10, 10, new Coordinates(5,5));
        phase.setHeight(map.getHeight());
        phase.setWidth(map.getWidth());
    }

    @Test
    public void shouldTurnOnTheLeft() {
        Game game = new Game(map); game.turnRight(); game.turnRight();
        phase.play(game);
        assertEquals(0, phase.getX()); assertEquals(1, phase.getY());
    }

}