import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange outer = CharRange.isNotIn('e', 'b'); // reversed order, should become ^b-e
        CharRange inner = CharRange.isIn('f', 'g');     // entirely outside [b,e]

        assertTrue(outer.contains(inner));
        assertEquals("^b-e", outer.toString());
    }
}