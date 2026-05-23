import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        assertTrue(CharRange.isIn('a', 'c').equals(CharRange.isIn('a', 'c')));
assertFalse(CharRange.isIn('a', 'c').equals(CharRange.isNotIn('a', 'c')));
    }
}