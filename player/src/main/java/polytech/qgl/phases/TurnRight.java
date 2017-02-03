package polytech.qgl.phases;

import flag.game.Game;

public class TurnRight extends Phase {

    private boolean finished = false;

    @Override boolean canBeApplied() { return !finished; }
    @Override void apply(Game game) {
        scan(game); game.turnRight(); game.move(); y++; game.turnRight();
        finished = true;
    }

}
