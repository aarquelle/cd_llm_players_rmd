import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        // reordered internally to a-e
CharRange r1 = CharRange.isNotIn('e', 'a');
CharRange r2 = CharRange.isNotIn('a', 'e');
assertTrue(r1.equals(r2));
assertEquals(r1.hashCode(), r2.hashCode());
    }
}