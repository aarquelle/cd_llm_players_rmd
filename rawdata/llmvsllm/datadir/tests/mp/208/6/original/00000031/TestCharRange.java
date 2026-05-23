import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange outer = CharRange.isNotIn('d', 'b'); // reversed -> b-d, negated
        CharRange inner = CharRange.isIn('c', 'c');

        assertFalse(outer.contains(inner));
        assertSame(outer.toString(), outer.toString());
    }
}