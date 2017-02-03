package polytech.qgl.players;

import flag.game.*;
import polytech.qgl.phases.*;

public class SimplePhasePlayer extends PhasePlayer {

    public SimplePhasePlayer() {
        phaseBinding.put(GoRight.class,   TurnRight.class);
        phaseBinding.put(TurnRight.class, GoLeft.class);
        phaseBinding.put(GoLeft.class,    TurnLeft.class);
        phaseBinding.put(TurnLeft.class,  GoRight.class);
        this.phase = new GoRight();
    }

    @Override
    protected boolean shouldStop() {
        boolean lastLine = ( phase.getY() == phase.getHeight() - 1);
        boolean inCorner = ( phase.getHeight() %2 ==0) ? phase.getX() == 0 : phase.getX() == phase.getWidth() - 1;
        return lastLine && inCorner;
    }

    @Override
    protected void explore(Game game) {
        applyStrategy(game);
        if (shouldStop()) { game.explore().ifPresent( id -> collect(id) ); }
    }

}
