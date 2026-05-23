import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange negated = CharRange.isNotIn('c', 'e');
        CharRange inside = CharRange.isIn('d', 'd');
        assertFalse(negated.contains(inside));

        CharRange reversed = CharRange.isIn('z', 'a');
        String s1 = reversed.toString();
        String s2 = reversed.toString();
        assertSame(s1, s2);
    }
}