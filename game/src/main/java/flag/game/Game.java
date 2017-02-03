package flag.game;


import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Game {

    private static final Coordinates START_POS = new Coordinates(0,0);
    private static final Compass.Heading START_HEADING = Compass.Heading.EAST;
    private static final int DEFAULT_FLAGS = 4;

    private GameMap map;
    private Compass compass;
    private Coordinates location;
    private int counter;

    public Game(GameMap m) {
        this.location = START_POS;
        this.compass = new Compass(START_HEADING);
        this.map = m;
        this.counter = 0;
    }

    GameMap getMap() { return this.map; }

    public Game() { this(GameMap.build(DEFAULT_FLAGS)); }

    public int howManyActionSpent() {
        return counter;
    }

    public void turnLeft() {
        counter++;
        this.compass.turnLeft();
    }

    public void turnRight() {
        counter++;
        this.compass.turnRight();
    }

    public void move() throws OutOfMapException {
        counter++;
        Coordinates next = compass.nextPosition(this.location);
        if(!map.contains(next))
            throw new OutOfMapException(map, next);
        this.location = next;
    }

    public Optional<String> explore() {
        counter++;
        return map.contentsAt(location);
    }

    public double score(List<String> flags) {
        return this.score(flags.toArray(new String[0]));
    }


    private static final double CHOSEN_ONE_BONUS = 4.0;
    private static final double FLAG_BONUS = 1.0;
    private static final double TIME_FACTOR = 10.0;

    public double score(String... flags) {
        double maximum = CHOSEN_ONE_BONUS + map.howManyFlags() * FLAG_BONUS;

        int found = (int) Arrays.stream(flags).filter( id -> map.contains(id) ).count();
        boolean chosenOne = Arrays.stream(flags).anyMatch( id -> map.isTheChosenOne(id));
        double raw = found * FLAG_BONUS + (chosenOne? CHOSEN_ONE_BONUS : 0.0) - (counter / TIME_FACTOR);

        return raw / maximum;
    }

}