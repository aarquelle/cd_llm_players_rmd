import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange negatedReversed = CharRange.isNotIn('e', 'a'); // should normalize to ^a-e

        assertEquals("^a-e", negatedReversed.toString());
        assertTrue(negatedReversed.contains(CharRange.isIn('f', 'g')));
    }
}