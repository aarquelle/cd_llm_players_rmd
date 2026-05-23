import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange negatedMiddle = CharRange.isNotIn('d', 'f');     // everything except d..f
        CharRange insideExcluded = CharRange.isIn('e', 'e');       // within d..f -> should NOT be contained
        CharRange disjointOutside = CharRange.isIn('a', 'c');      // outside excluded -> should be contained

        assertAll(
                () -> assertFalse(negatedMiddle.contains(insideExcluded)),
                () -> assertTrue(negatedMiddle.contains(disjointOutside))
        );
    }
}