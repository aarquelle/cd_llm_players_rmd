import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange negatedOuter = CharRange.isNotIn('d', 'f');   // everything except d-f
        CharRange insideHole = CharRange.isIn('e', 'e');        // within d-f -> should NOT be contained
        assertFalse(negatedOuter.contains(insideHole));

        CharRange negatedInnerWider = CharRange.isNotIn('c', 'g'); // hole c-g covers d-f -> should be contained
        assertTrue(negatedOuter.contains(negatedInnerWider));
    }
}