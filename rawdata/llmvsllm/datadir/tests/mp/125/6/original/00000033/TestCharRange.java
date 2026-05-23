import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange outer = CharRange.isNotIn('z', 'd'); // constructor must reorder to d-z and keep negated
        CharRange innerInside = CharRange.isIn('f', 'y');

        assertFalse(outer.contains(innerInside));
        assertEquals("^d-z", outer.toString());
    }
}