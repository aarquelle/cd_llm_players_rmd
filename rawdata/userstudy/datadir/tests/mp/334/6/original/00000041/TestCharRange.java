import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange canonical = CharRange.isIn('a', 'c');
        assertTrue(canonical.equals(CharRange.isIn('c', 'a')));
        assertFalse(canonical.equals("a-c"));
    }
}