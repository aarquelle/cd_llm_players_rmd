import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange outer = CharRange.isNotIn('z', 'a'); // reversed, negated => ^a-z
        assertTrue(outer.contains(CharRange.is('0'))); // outside [a-z], thus contained by negation
        assertEquals("^a-z", outer.toString());
    }
}