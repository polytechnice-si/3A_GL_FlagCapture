package flag.game;

import org.junit.*;


import static org.mockito.Mockito.*;

public class PlayerTest {

    private Player player;
    @Before public void initPlayer() { player = spy(Player.class); }

    private Game game;
    @Before public void initGame() { game = spy(Game.class); }

    @Test
    public void shouldStopPlayingWhenTellingSo() {
        when(player.shouldStop()).thenReturn(true);
        player.run(game);
        verify(player, times(1)).init(game.getMap());
        verify(player, times(1)).shouldStop();
        verify(player, times(0)).explore(game);
        verify(game, times(1)).score(anyList());
    }

    @Test
    public void shouldPlayABitIfNecessary() {
        when(player.shouldStop()).thenReturn(false).thenReturn(false).thenReturn(true);
        player.run(game);
        verify(player, times(1)).init(game.getMap()); // always one single initialization
        verify(player, times(3)).shouldStop();        // asked N times to continue
        verify(player, times(2)).explore(game);       // performed N-1 exploration on the map;
        verify(game, times(1)).score(anyList());      // sconre computed only once on the game
    }

}
