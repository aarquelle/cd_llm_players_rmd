import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange r = CharRange.isNotIn('z', 'a'); // constructor will swap to a-z, negated=true
        int expected = 83 + 'a' + 7 * 'z' + 1;

        assertEquals(expected, r.hashCode());
        assertNotEquals(CharRange.isIn('a', 'z').hashCode(), r.hashCode());
    }
}