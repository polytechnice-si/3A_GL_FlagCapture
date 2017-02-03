package polytech.qgl.players;

import flag.game.*;
import polytech.qgl.phases.*;


public class SimpleIntermediatePlayer extends Player {

    private Phase phase = new GoRight();

    @Override
    protected void init(GameMap map) {
        phase.setWidth(map.getWidth());
        phase.setHeight(map.getHeight());
        phase.setPlayer(this);
    }

    @Override
    protected boolean shouldStop() {
        int x = phase.getX(); int y = phase.getY(); int w = phase.getWidth(); int h = phase.getHeight();
        boolean lastLine = (y == h - 1);
        boolean inCorner = ( h % 2 ==0) ? x == 0 : x == w - 1;
        return lastLine && inCorner;
    }

    @Override
    protected void explore(Game game) {
        phase.play(game);
        phase = next();
        if (shouldStop()) { game.explore().ifPresent( id -> collect(id) ); }
    }

    private Phase next() {
        Phase next;
        if(phase.getClass() == GoRight.class) {
            next = new TurnRight();
        } else if (phase.getClass() == TurnRight.class) {
            next = new GoLeft();
        } else if (phase.getClass() == GoLeft.class) {
            next = new TurnLeft();
        } else if (phase.getClass() == TurnLeft.class) {
            next = new GoRight();
        } else {
            throw new RuntimeException("Unable to instantiate next phase");
        }
        next.pursue(phase);
        return next;
    }

}