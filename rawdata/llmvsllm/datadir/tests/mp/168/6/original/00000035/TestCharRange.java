import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange neg = CharRange.isNotIn('c', 'a'); // reversed order -> a-c, negated
        assertEquals("^a-c", neg.toString());
        assertFalse(neg.contains(CharRange.is('b'))); // b is within a-c, so negated must not contain it
    }
}