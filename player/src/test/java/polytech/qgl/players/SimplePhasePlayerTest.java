package polytech.qgl.players;


import flag.game.Coordinates;
import flag.game.Game;
import flag.game.GameMap;
import flag.game.Player;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

public class SimplePhasePlayerTest {

    private GameMap map;
    private Game game;

    @Before public void init() {
        map = GameMap.build(1);
        game = new Game(map);
    }

    @Before public void initGame() { game = new Game(GameMap.build(3)); }

    @Test
    public void shouldFindAllTheFlagsOnTheMap() {
        Player player = spy(SimplePhasePlayer.class);
        player.run(game);
        verify(player, times(map.howManyFlags())).collect(anyString());
    }

    @Test
    public void willSeeFlagsLocatedOnCorners() {
        Player player = spy(SimplePhasePlayer.class);
        map = new GameMap(10, 10,
                          new Coordinates(0,0), new Coordinates(9,0), new Coordinates(9,9));
        game = new Game(map);
        double score = player.run(game);
        assertTrue(score <= 1.0);
        verify(player, times(3)).collect(anyString());
    }

    @Test
    public void evenIfLocatedInTheLastEncounteredCorner() {
        Player player = spy(SimplePhasePlayer.class);
        game = new Game(new GameMap(10, 10, new Coordinates(0,9)));
        player.run(game);
        verify(player, times(1)).collect(anyString());
    }

}