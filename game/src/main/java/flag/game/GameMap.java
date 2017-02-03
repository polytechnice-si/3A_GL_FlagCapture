package flag.game;

import java.util.*;

public class GameMap {


    static final int RANDOM_MAP_MAX_SIZE = 100;
    private int height;
    private int width;
    private Map<Coordinates, Flag> flags;
    private Coordinates chosenOne;


    public GameMap(int width, int height, Coordinates flag, Coordinates... others) {
        this.height = height;
        this.width  = width;
        List<Coordinates> flagLocations = new ArrayList<>(Arrays.asList(others));
        flagLocations.add(flag);
        initFlags(flagLocations);
    }


    public static GameMap build(int flags) {
        if(flags < 1)
            throw new IllegalArgumentException("#flags must be >=1" + flags);
        Random rand = new Random();
        int height = rand.nextInt(RANDOM_MAP_MAX_SIZE) + 1;
        int width  = rand.nextInt(RANDOM_MAP_MAX_SIZE) + 1;
        List<Coordinates> locations = new ArrayList<>();
        for(int i = 0; i < flags; i++) {
            locations.add(new Coordinates(rand.nextInt(width), rand.nextInt(height)));
        }
        Coordinates first = locations.get(0);
        Coordinates[] others = locations.subList(1,locations.size()).toArray(new Coordinates[0]);
        return new GameMap(width, height, first, others);
    }


    public int howManyFlags() { return this.flags.size(); }
    public int getWidth() {  return width; }
    public int getHeight() { return height; }

    boolean contains(Coordinates c) {
        return c.getX() >= 0 && c.getX() < width && c.getY() >= 0 && c.getY() < height;
    }

    boolean contains(String id) {
        return flags.values().stream().anyMatch( f -> f.identify().equals(id));
    }

    boolean isTheChosenOne(String identifier) {
        String value = flags.get(chosenOne).identify();
        return value.equals(identifier);
    }

    Optional<String> contentsAt(Coordinates location) {
        if (flags.containsKey(location))
            return Optional.of(flags.get(location).identify());
        else
            return Optional.empty();
    }

    private void initFlags(List<Coordinates> coordinates) {
        this.flags  = new HashMap<>();
        for(Coordinates c: coordinates) {
            if(contains(c)) {
                flags.put(c, new Flag());
            } else {
                throw new OutOfMapException(this, c);
            }
        }
        Random rand = new Random();
        this.chosenOne = coordinates.get(rand.nextInt(coordinates.size()));
    }



}