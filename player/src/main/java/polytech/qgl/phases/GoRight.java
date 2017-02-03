package polytech.qgl.phases;

import flag.game.Game;


public class GoRight extends Phase {

    @Override public boolean canBeApplied() { return x < width - 1; }
    @Override public void apply(Game game)  { scan(game); game.move(); x++; }

}