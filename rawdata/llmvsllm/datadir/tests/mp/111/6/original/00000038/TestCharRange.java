import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange negOuter = CharRange.isNotIn('d', 'b'); // reversed -> b-d, negated
        CharRange inner = CharRange.isIn('b', 'c');

        assertFalse(negOuter.contains(inner));
        assertEquals("^b-d", negOuter.toString());
    }
}