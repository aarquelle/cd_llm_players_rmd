import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange range = CharRange.isIn('d', 'a'); // constructor will reorder to a-d
        assertEquals(83 + 'a' + 7 * 'd' + 0, range.hashCode());

        assertNotEquals(CharRange.is('x').hashCode(), CharRange.isNot('x').hashCode());
    }
}