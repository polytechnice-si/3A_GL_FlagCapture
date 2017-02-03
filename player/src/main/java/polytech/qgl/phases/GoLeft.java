package polytech.qgl.phases;

import flag.game.Game;

public class GoLeft extends Phase {

    @Override public boolean canBeApplied() { return x > 0; }
    @Override public void apply(Game game) { scan(game); game.move(); x--; }

}