import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange negated = CharRange.isNotIn('d', 'f');
        CharRange inside = CharRange.isIn('e', 'e');
        assertFalse(negated.contains(inside));

        CharRange reversed = CharRange.isIn('f', 'd');
        assertEquals("d-f", reversed.toString());
    }
}