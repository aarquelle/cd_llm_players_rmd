import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange aToC = CharRange.isIn('c', 'a'); // normalized to a-c
        assertEquals("a-c", aToC.toString());

        CharRange negatedB = CharRange.isNot('b'); // everything except b
        assertFalse(negatedB.contains(aToC)); // a-c intersects excluded 'b' so should be false
    }
}