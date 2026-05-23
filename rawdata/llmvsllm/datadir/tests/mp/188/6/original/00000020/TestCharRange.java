import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        CharRange r1 = CharRange.isIn('a', 'e');
CharRange r2 = CharRange.isNotIn('a', 'e');
assertNotEquals(r1.hashCode(), r2.hashCode());
assertEquals(83 + 'a' + 7 * 'e', r1.hashCode());
    }
}