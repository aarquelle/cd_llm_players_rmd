import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange outerNeg = CharRange.isNotIn('d', 'f');  // excludes d..f
        CharRange innerNeg = CharRange.isNotIn('e', 'e');  // excludes only e
        CharRange disjointPos = CharRange.isIn('a', 'c');  // entirely outside excluded block

        assertFalse(outerNeg.contains(innerNeg));
        assertTrue(outerNeg.contains(disjointPos));
    }
}