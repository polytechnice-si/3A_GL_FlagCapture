package polytech.qgl.players;


import flag.game.*;
import org.junit.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class SimplePlayerTest {

    private GameMap map;
    private Game game;

    @Before public void init() {
        map = GameMap.build(1);
        game = new Game(map);
    }

    @Before public void initGame() { game = new Game(GameMap.build(1)); }

    @Test
    public void shouldAtLeastPlayAsOneCannotTestAnythingElseInThisStupidImplementation() {
        Player player = spy(SimplePlayer.class);
        double score = player.run(game); // aka "fingers crossed"
        assertTrue(score <= 1.0);
        // For each line, scan current tile than move until the last tile -> 2h*(w-1)
        // For each line, scan, turn, move and turn to do a UTurn -> 4*(h-1)
        int nbActions = (map.getHeight() * 2 * (map.getWidth() -1)) + (4 * (map.getHeight()-1));
        // Do not forget the final scan, on the last tile.
        assertEquals(nbActions + 1, game.howManyActionSpent());
        verify(player, times(map.howManyFlags())).collect(anyString());
    }


    @Test
    public void willSeeFlagsLocatedOnCorners() {
        Player player = spy(SimplePlayer.class);
        map = new GameMap(10, 10,
                          new Coordinates(0,0), new Coordinates(9,0), new Coordinates(9,9));
        game = new Game(map);
        double score = player.run(game);
        assertTrue(score <= 1.0);
        verify(player, times(3)).collect(anyString());
    }

    @Test
    public void evenIfLocatedInTheLastEncounteredCorner() {
        Player player = spy(SimplePlayer.class);
        game = new Game(new GameMap(10, 10, new Coordinates(0,9)));
        player.run(game);
        verify(player, times(1)).collect(anyString());
    }

}