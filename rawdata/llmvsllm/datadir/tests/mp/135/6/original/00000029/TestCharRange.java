import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange range = CharRange.isNotIn('z', 'a'); // constructor should normalize to 'a'..'z' with negated=true
        assertEquals(83 + 'a' + 7 * 'z' + 1, range.hashCode());
        assertNotEquals(CharRange.isIn('a', 'z').hashCode(), range.hashCode());
    }
}