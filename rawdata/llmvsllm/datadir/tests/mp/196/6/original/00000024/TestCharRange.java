import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        // normalized to a-e
CharRange a = CharRange.isNotIn('e', 'a');
CharRange b = CharRange.isNotIn('a', 'e');
assertTrue(a.equals(b));
assertEquals(a.hashCode(), b.hashCode());
    }
}