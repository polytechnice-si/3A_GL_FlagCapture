package flag.game;


public class OutOfMapException extends RuntimeException {

    public OutOfMapException(GameMap map, Coordinates loc) {
        super(String.format("Location %s isn't a part of a [%s,%s] map",loc, map.getWidth(), map.getHeight()));
    }

}
