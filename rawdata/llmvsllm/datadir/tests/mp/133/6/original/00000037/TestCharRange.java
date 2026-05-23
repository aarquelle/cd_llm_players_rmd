import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange r = CharRange.isNotIn('e', 'a'); // should normalize to a-e and be negated
        assertEquals("^a-e", r.toString());
        assertEquals("truefalsetruefalse",
                "" + r.contains('z') + r.contains('c') + r.contains(CharRange.isIn('f', 'g')) + r.contains(CharRange.isIn('b', 'd')));
    }
}