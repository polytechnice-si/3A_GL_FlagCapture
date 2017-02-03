package polytech.qgl.phases;

import flag.game.Game;

public class TurnLeft extends Phase {

    private boolean finished = false;

    @Override boolean canBeApplied() { return !finished; }
    @Override void apply(Game game) {
        scan(game); game.turnLeft(); game.move(); y++; game.turnLeft();
        finished = true;
    }

}
