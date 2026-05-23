import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        CharRange big = CharRange.isNotIn('c', 'x');
CharRange small = CharRange.isNotIn('d', 'w');
assertTrue(big.contains(small));
assertFalse(small.contains(big));
    }
}