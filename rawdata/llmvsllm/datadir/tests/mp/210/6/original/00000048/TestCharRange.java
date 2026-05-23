import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange negatedOuter = CharRange.isNotIn('d', 'b'); // should normalize to b-d and be negated
        CharRange negatedInner = CharRange.isNotIn('c', 'a'); // should normalize to a-c and be negated

        assertTrue(negatedOuter.contains(negatedInner));
        assertEquals("^b-d", negatedOuter.toString());
    }
}