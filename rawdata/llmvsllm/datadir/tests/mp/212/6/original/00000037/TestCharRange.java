import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange reversed = CharRange.isIn('z', 'a');
        assertEquals("a-z", reversed.toString());

        CharRange notIn = CharRange.isNotIn('b', 'd');
        assertFalse(notIn.contains(CharRange.isIn('c', 'c')));
    }
}