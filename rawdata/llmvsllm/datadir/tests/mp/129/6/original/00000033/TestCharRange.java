import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange outer = CharRange.isNotIn('z', 'b'); // should normalize to ^b-z
        assertEquals("^b-z", outer.toString());
        assertTrue(outer.contains(CharRange.is('a')));
    }
}