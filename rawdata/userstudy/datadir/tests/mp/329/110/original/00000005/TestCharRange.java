import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        assertFalse(CharRange.is('a').isNegated());
        assertTrue(CharRange.isNot('a').isNegated());
    }
}