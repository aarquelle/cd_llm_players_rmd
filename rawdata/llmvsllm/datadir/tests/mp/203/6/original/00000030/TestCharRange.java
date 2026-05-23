import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange neg = CharRange.isNotIn('c', 'd'); // everything except c-d
        CharRange pos = CharRange.isIn('a', 'b');    // a-b

        assertTrue(neg.contains(pos));
        assertFalse(pos.contains(neg));
    }
}