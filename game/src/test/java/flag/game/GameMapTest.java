package flag.game;


import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;

public class GameMapTest {

    @Test(expected = OutOfMapException.class)
    public void shouldRejectOutOfBoundsFlag() { new GameMap(10, 10, new Coordinates(10,10)); }

    @Test(expected = OutOfMapException.class)
    public void shouldRejectNegativeWidth() { new GameMap(-10, 10, new Coordinates(1,1)); }

    @Test(expected = OutOfMapException.class)
    public void shouldRejectNegativeHeight() { new GameMap(10, -10, new Coordinates(1,1)); }

    @Test(expected = OutOfMapException.class)
    public void shouldRejectNegativeLocation() { new GameMap(10, 10, new Coordinates(-1,-1)); }

    @Test
    public void shouldIdentifyOutOfBoundValues() {
        GameMap map = new GameMap(10, 10, new Coordinates(1,1));
        assertFalse(map.contains(new Coordinates(10, 10)));
    }

    @Test
    public void shouldStoreFlags() {
        Coordinates flag1 = new Coordinates(1, 1);
        Coordinates flag2 = new Coordinates(1, 2);
        GameMap map = new GameMap(10, 10, flag1, flag2);
        assertEquals(2, map.howManyFlags());
        assertTrue(map.contentsAt(flag1).isPresent());
        assertTrue(map.contentsAt(flag2).isPresent());
        assertFalse(map.contentsAt(new Coordinates(0,0)).isPresent());
    }

    @Test
    public void shouldIdentifyTheChosenOne() {
        Coordinates loc = new Coordinates(5,5);
        GameMap map = new GameMap(10, 10, loc);
        String found = map.contentsAt(loc).orElse("");
        assertTrue(map.isTheChosenOne(found));
    }

    @Test
    public void shouldIdentifyTheChosenOneAmongMultiples() {
        Coordinates loc1 = new Coordinates(5,5);
        Coordinates loc2 = new Coordinates(3,3);
        GameMap map = new GameMap(10, 10, loc1, loc2);
        String found1 = map.contentsAt(loc1).orElse("");
        String found2 = map.contentsAt(loc2).orElse("");
        assertTrue(map.isTheChosenOne(found1) || map.isTheChosenOne(found2));
    }

    @Test
    public void shouldBuildRandomMaps() {
        for(int i = 0; i < 1000; i++) {
            GameMap random = GameMap.build(3);
            assertTrue(random.howManyFlags() >= 1 && random.howManyFlags() <= 3);
            assertTrue(random.getWidth() > 0 && random.getWidth() <= GameMap.RANDOM_MAP_MAX_SIZE);
            assertTrue(random.getHeight() > 0 && random.getHeight() <= GameMap.RANDOM_MAP_MAX_SIZE);
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldRejectInvalidNumberOfFlags() {
        GameMap.build(0);
    }


    @Test
    public void shouldKnowItsBoundary() {
        GameMap map = new GameMap(10,10, new Coordinates(0,0));
        assertTrue(map.contains(new Coordinates(0,0)));
        assertTrue(map.contains(new Coordinates(0,9)));
        assertTrue(map.contains(new Coordinates(9,0)));
        assertTrue(map.contains(new Coordinates(9,9)));
    }

    @Test
    @Ignore
    public void shouldComputeTheDistanceBetweenTheChosenOneAndTheCenter() {

    }

}
