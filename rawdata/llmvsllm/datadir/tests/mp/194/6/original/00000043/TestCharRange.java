import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange outer = CharRange.isNotIn('d', 'b'); // should normalize to ^b-d
        CharRange inner = CharRange.isNotIn('c', 'c');

        assertEquals("^b-d", outer.toString());
        assertFalse(outer.contains(inner));
    }
}