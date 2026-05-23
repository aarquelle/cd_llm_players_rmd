import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange universe = CharRange.isIn((char) 0, Character.MAX_VALUE);
        CharRange aToC = CharRange.isIn('a', 'c');
        CharRange notB = CharRange.isNot('b');

        assertTrue(universe.contains(notB));
        assertFalse(notB.contains(aToC));
    }
}