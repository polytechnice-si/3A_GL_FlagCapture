package flag.game;


import org.junit.Test;
import static org.junit.Assert.*;

public class FlagTest {

    @Test
    public void shouldBeIdentifiable(){
        Flag f = new Flag();
        assertNotNull(f.identify());
    }

    @Test
    public void shouldBeUnique(){
        Flag f = new Flag();
        Flag fPrime = new Flag();
        assertNotEquals(f, fPrime);
        assertNotEquals(f.identify(), fPrime.identify());
    }

}
