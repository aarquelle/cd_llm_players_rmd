import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange outerNeg = CharRange.isNotIn('d', 'f');   // excludes d..f
        CharRange innerPosDisjoint = CharRange.isIn('a', 'c');
        CharRange innerNegWider = CharRange.isNotIn('b', 'g');

        assertAll(
            () -> assertTrue(outerNeg.contains(innerPosDisjoint)),
            () -> assertFalse(outerNeg.contains(innerNegWider))
        );
    }
}