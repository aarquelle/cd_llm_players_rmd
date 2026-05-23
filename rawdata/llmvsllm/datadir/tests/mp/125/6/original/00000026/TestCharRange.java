import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange r = CharRange.isNotIn('b', 'd'); // excludes b..d, includes everything else
        assertAll(
                () -> assertFalse(r.contains('b')),
                () -> assertTrue(r.contains('a'))
        );
    }
}