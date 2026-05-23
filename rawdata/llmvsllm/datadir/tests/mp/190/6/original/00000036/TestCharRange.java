import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange outer = CharRange.isNotIn('f', 'b'); // reversed order; should become ^b-f
        CharRange inner = CharRange.isIn('c', 'd');    // lies within b-f

        assertEquals("^b-f", outer.toString());
        assertFalse(outer.contains(inner));
    }
}