import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange outer = CharRange.isNotIn('m', 'p'); // everything except m-p
        assertTrue(outer.contains(CharRange.isIn('a', 'b')));
        assertFalse(outer.contains(CharRange.isIn('p', 'p')));
    }
}