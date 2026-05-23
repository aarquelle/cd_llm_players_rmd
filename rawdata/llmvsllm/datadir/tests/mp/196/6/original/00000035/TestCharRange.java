import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange negated = CharRange.isNotIn('c', 'f'); // contains everything except c-f
        assertFalse(negated.contains(CharRange.isIn('c', 'c')));
        assertTrue(negated.contains(CharRange.isIn('b', 'b')));
    }
}