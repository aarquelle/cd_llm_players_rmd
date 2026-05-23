import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange outer = CharRange.isNotIn('z', 'a'); // becomes ^a-z
        CharRange inner = CharRange.isNotIn('z', 'c'); // becomes ^c-z, should be contained in ^a-z

        assertTrue(outer.contains(inner));
        assertEquals("^a-z", outer.toString());
    }
}