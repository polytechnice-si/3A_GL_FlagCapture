package flag.game;


import org.junit.*;

import static org.junit.Assert.*;

public class GameTest {

    private Game game;

    @Before
    public void initGame() {
        game = new Game(new GameMap(10, 10, new Coordinates(0,0)));
    }

    @Test
    public void shouldCountActions() {
        assertEquals(0, game.howManyActionSpent());
        game.move();
        game.turnLeft();
        game.turnRight();
        game.explore();
        assertEquals(4, game.howManyActionSpent());
    }

    @Test(expected = OutOfMapException.class)
    public void shouldDetectGoingOut() {
        // Starting (0,0) facing East, so making a U-Turn and then move go out by construction
        game.turnLeft();
        game.turnLeft();
        game.move();
    }

    @Test
    public void shouldAssignScore() {
        assertEquals(0.0, game.score(), 0.001);
        assertEquals(0.0, game.score("unknown"), 0.001);
        String id = game.explore().get();
        assertEquals(1.0, game.score(id), 0.1); // chosenOne = 400, flag = 100, 1 action => no malus;
        for(int i = 0; i < 100; i++)
            game.turnLeft();
        assertTrue(game.score(id) < 1.0);
    }

}