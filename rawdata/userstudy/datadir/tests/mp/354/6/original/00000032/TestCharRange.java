import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange universe = CharRange.isIn((char) 0, Character.MAX_VALUE);
        CharRange notA = CharRange.isNot('a');

        assertAll(
                () -> assertTrue(universe.contains(notA)),
                () -> assertTrue(CharRange.isNotIn('m', 'p').contains(CharRange.isIn('a', 'b')))
        );
    }
}