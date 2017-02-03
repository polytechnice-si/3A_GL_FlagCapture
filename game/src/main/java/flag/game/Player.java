package flag.game;

import java.util.*;

public abstract class Player {


    private List<String> collected = new ArrayList<>();
    public void collect(String flagId) {
        collected.add(flagId);
    }


    protected abstract void init(GameMap map);
    protected abstract boolean shouldStop();
    protected abstract void explore(Game game);


    public double run(Game game) {
        init(game.getMap());
        while(!shouldStop()) {
            explore(game);
        }
        return game.score(collected);
    }
}
