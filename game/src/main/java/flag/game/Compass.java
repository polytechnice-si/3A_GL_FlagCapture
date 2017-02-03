package flag.game;

import java.util.EnumMap;
import java.util.Map;
import java.util.function.Function;
import static flag.game.Compass.Heading.*;

class Compass {

    enum Heading { NORTH, SOUTH, EAST, WEST }

    private Heading heading;

    Compass(Heading heading) {
        this.heading = heading;
    }

    Heading getHeading() { return heading; }

    void turnLeft() { // Design #1: using a simple switch case
        switch (heading) {
            case NORTH:
                this.heading = WEST; break;
            case EAST:
                this.heading = NORTH; break;
            case SOUTH:
                this.heading = EAST; break;
            case WEST:
                this.heading = SOUTH; break;
        }
    }

    void turnRight() { // Design #2: using an enumerated map
        Map<Heading,Heading> goingRight = new EnumMap<>(Heading.class);
        goingRight.put(NORTH, EAST);
        goingRight.put(EAST, SOUTH);
        goingRight.put(SOUTH,WEST);
        goingRight.put(WEST, NORTH);
        this.heading = goingRight.get(this.heading); // problem: the map should not be instantiated each time!
    }

    Coordinates nextPosition(Coordinates location) { // Design #3: using a static map & anonymous functions
        return movingTable.get(this.heading).apply(location);
    }

    private static Map<Heading,Function<Coordinates,Coordinates>> movingTable = new EnumMap<>(Heading.class);
    static {
        movingTable.put(NORTH, c -> new Coordinates(c.getX(),     c.getY() - 1));
        movingTable.put(EAST, c -> new Coordinates(c.getX() + 1, c.getY()));
        movingTable.put(SOUTH, c -> new Coordinates(c.getX(),     c.getY() + 1));
        movingTable.put(WEST, c -> new Coordinates(c.getX() - 1, c.getY()));
    }

}

