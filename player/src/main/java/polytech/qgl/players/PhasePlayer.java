package polytech.qgl.players;


import flag.game.*;
import polytech.qgl.phases.Phase;

import java.util.HashMap;
import java.util.Map;

abstract class PhasePlayer extends Player {

    protected Map<Class<? extends Phase>,Class<? extends Phase>> phaseBinding = new HashMap<>();

    protected Phase phase;

    @Override
    protected void init(GameMap map) {
        phase.setWidth(map.getWidth());
        phase.setHeight(map.getHeight());
        phase.setPlayer(this);
    }

    protected void applyStrategy(Game game) {
        phase.play(game);
        try {
            Phase next = phaseBinding.get(phase.getClass()).newInstance();
            next.pursue(phase);
            this.phase = next;
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException("Unable to instantiate next phase");
        }
    }


}
