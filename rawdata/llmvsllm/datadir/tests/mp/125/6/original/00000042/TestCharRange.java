import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange reversed = CharRange.isIn('z', 'a'); // normalized to a-z
        assertEquals(83 + 'a' + 7 * 'z' + 0, reversed.hashCode());
        assertEquals(83 + 'b' + 7 * 'b' + 1, CharRange.isNot('b').hashCode());
    }
}