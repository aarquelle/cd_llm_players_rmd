import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange r = CharRange.isNotIn('z', 'a'); // should reorder to a-z and be negated

        boolean containsZ = r.contains('z'); // inside reordered range -> negated => false
        String s1 = r.toString();
        String s2 = r.toString(); // should return cached instance

        assertFalse(containsZ);
        assertSame(s1, s2);
    }
}