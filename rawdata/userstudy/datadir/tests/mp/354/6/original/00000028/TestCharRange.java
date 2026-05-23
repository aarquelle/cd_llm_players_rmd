import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange negatedOuter = CharRange.isNotIn('c', 'f'); // everything except c-f
        CharRange insideExcluded = CharRange.isIn('d', 'e');  // overlaps excluded -> should NOT be contained
        assertFalse(negatedOuter.contains(insideExcluded));

        CharRange universe = CharRange.isIn((char) 0, Character.MAX_VALUE);
        assertTrue(universe.contains(CharRange.isNot('x')));
    }
}