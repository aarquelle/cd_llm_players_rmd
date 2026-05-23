import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange outer = CharRange.isNotIn('z', 'a'); // normalizes to ^a-z
        CharRange disjoint = CharRange.isIn('A', 'Z'); // completely outside a-z

        assertTrue(outer.contains(disjoint));
        assertEquals("^a-z", outer.toString());
    }
}