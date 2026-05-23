import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange neg = CharRange.isNotIn('z', 'a'); // should normalize to ^a-z
        assertEquals("^a-z", neg.toString());

        assertFalse(neg.contains(CharRange.isIn('b', 'c'))); // inner range is within excluded block -> not contained
    }
}