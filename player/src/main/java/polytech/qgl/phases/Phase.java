package polytech.qgl.phases;


import flag.game.Game;
import flag.game.Player;

import java.util.Optional;

abstract public class Phase {

    protected int width;
    protected int height;
    protected int x = 0;
    protected int y = 0;
    protected Player player;

    public int getX() { return x; }
    public int getY() { return y; }
    void setX(int x) { this.x = x; }
    void setY(int y) { this.y = y; }
    public int getWidth() { return this.width; }
    public void setWidth(int w) { this.width = w; }
    public int getHeight() { return this.height; }
    public void setHeight(int h) { this.height = h; }
    public void setPlayer(Player player) { this.player = player; }

    public void play(Game game) {
        while( canBeApplied() ) { apply(game); }
    }

    abstract boolean canBeApplied();
    abstract void apply(Game game);

    public void pursue(Phase previous) {
        this.width = previous.width;
        this.height = previous.height;
        this.x = previous.x;
        this.y = previous.y;
        this.player = previous.player;
    }

    protected void scan(Game game) {
        Optional<String> flag = game.explore();
        if (flag.isPresent()) { player.collect(flag.get()); }
    }

    @Override
    public String toString() {
        return "Phase {" +
                this.getClass().getCanonicalName() +
                ", w=" + width +
                ", h=" + height +
                ", x=" + x +
                ", y=" + y +
                '}';
    }
}