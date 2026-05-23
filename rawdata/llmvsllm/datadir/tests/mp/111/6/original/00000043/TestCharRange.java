import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange outer = CharRange.isNotIn('f', 'c'); // normalized to ^c-f
        CharRange inner = CharRange.isIn('a', 'b');

        assertTrue(outer.contains(inner));
        assertSame("^c-f", outer.toString(), outer.toString());
    }
}