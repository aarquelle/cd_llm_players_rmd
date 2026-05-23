import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange outer = CharRange.isNotIn('c', 'a'); // normalized to ^a-c
        CharRange disjoint = CharRange.isIn('a', 'c'); // a-c is fully excluded by ^a-c

        assertTrue(outer.contains(disjoint));
        assertEquals("^a-c", outer.toString());
    }
}