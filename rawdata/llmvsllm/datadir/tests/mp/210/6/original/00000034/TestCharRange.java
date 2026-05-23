import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange outer = CharRange.isNotIn('d', 'f'); // everything except d-f
        assertAll(
                () -> assertTrue(outer.contains(CharRange.isIn('a', 'c'))),
                () -> assertFalse(outer.contains(CharRange.isNotIn('c', 'g')))
        );
    }
}