package flag.game;


import java.util.UUID;

public class Flag {

    private UUID id;

    Flag() {
        this.id = UUID.randomUUID();
    }

    String identify() {
        return this.id.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flag flag = (Flag) o;
        return id.equals(flag.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
