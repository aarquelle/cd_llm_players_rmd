import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        assertEquals("^a", CharRange.isNot('a').toString());
assertEquals("^a-c", CharRange.isNotIn('a', 'c').toString());
    }
}