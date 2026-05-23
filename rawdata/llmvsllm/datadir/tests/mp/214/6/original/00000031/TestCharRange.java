import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                assertAll(
                () -> assertTrue(CharRange.isNotIn('d', 'f').contains(CharRange.isIn('a', 'c'))),
                () -> assertTrue(CharRange.isIn((char) 0, Character.MAX_VALUE).contains(CharRange.isNotIn('m', 'o')))
        );
    }
}