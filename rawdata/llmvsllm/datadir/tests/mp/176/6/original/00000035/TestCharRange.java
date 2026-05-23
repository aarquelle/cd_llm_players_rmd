import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange negated = CharRange.isNotIn('f', 'd'); // normalize to d-f and negate

        assertTrue(negated.contains(CharRange.is('a')));
        assertEquals("^d-f", negated.toString());
    }
}