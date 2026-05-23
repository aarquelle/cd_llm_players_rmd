import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange base = CharRange.isNotIn('c', 'a'); // should reorder to a-c
        assertTrue(base.contains(CharRange.isNotIn('a', 'c')));
        assertEquals("^a-c", base.toString());
    }
}