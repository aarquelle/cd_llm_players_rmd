import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange reversed = CharRange.isIn('e', 'a'); // normalized to a-e
        assertEquals(CharRange.isIn('a', 'e'), reversed);
        assertNotEquals(reversed, CharRange.isNotIn('a', 'e'));
    }
}