package flag.game;

public class Coordinates {

    private int x, y;

    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() { return x; }
    public int getY() { return y; }


    double distance(Coordinates that) {
        double xP = (double) that.x;
        double yP = (double) that.y;
        return Math.sqrt( ((x-xP) * (x-xP)) + ((y-yP) * (y-yP)));
    }

    @Override // Auto-generated by IntelliJ
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinates that = (Coordinates) o;
        if (x != that.x) return false;
        return y == that.y;
    }

    @Override // Auto-generated by IntelliJ
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }

    @Override
    public String toString() { return "(x:" + x + ", y:" + y + ')'; }
}
