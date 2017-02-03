package polytech.qgl.players;

import flag.game.*;

import java.util.Optional;

public class SimplePlayer extends Player {

    private int height, width, x, y;
    private boolean moveLeft, moveRight, uTurnRight, uTurnLeft;
    private boolean isOnLastTile = false;

    @Override
    protected void init(GameMap map) {
        this.height = map.getHeight(); this.width = map.getWidth();
        this.x = 0; this.y = 0;
        goToRight();
    }

    @Override
    protected boolean shouldStop() {
        if(isOnLastTile)
            return true;
        boolean bottomLeftCorner  = uTurnLeft  && x == 0 && y == height -1;
        boolean bottomRightCorner = uTurnRight && x == width - 1 && y == height -1;
        if (bottomLeftCorner || bottomRightCorner)
            isOnLastTile = true;
        return false;
    }

    @Override
    protected void explore(Game game) {
        if(isOnLastTile) {
            scan(game); return;
        }
        if(moveRight) {
            while(x < width - 1) {
                scan(game); game.move(); x++;
            }
            turnRight();
            return;
        }
        if(uTurnRight) {
            scan(game); game.turnRight(); game.move(); y++; game.turnRight();
            goToLeft();
            return;
        }
        if(moveLeft) {
            while(x > 0) {
                scan(game); game.move(); x--;
            }
            turnLeft();
            return;
        }
        if (uTurnLeft) {
            scan(game); game.turnLeft(); game.move(); y++; game.turnLeft();
            goToRight();
        }
    }

    private void scan(Game game) {
        Optional<String> flag = game.explore();
        if (flag.isPresent()) { collect(flag.get()); }
    }

    private void goToLeft() {
        moveLeft = true;   moveRight = false;
        uTurnLeft = false; uTurnRight = false;
    }

    private void goToRight() {
        moveLeft = false;  moveRight = true;
        uTurnLeft = false; uTurnRight = false;
    }

    private void turnRight() {
        moveLeft = false;  moveRight = false;
        uTurnLeft = false; uTurnRight = true;
    }

    private void turnLeft() {
        moveLeft = false; moveRight = false;
        uTurnLeft = true; uTurnRight = false;
    }

    @Override
    public String toString() {
        return "SimplePlayer{" +
                "height=" + height +
                ", width=" + width +
                ", x=" + x +
                ", y=" + y +
                ", moveLeft=" + moveLeft +
                ", moveRight=" + moveRight +
                ", uTurnRight=" + uTurnRight +
                ", uTurnLeft=" + uTurnLeft +
                '}';
    }
}