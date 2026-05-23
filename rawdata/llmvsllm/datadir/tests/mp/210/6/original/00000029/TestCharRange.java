import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange outerNeg = CharRange.isNotIn('c', 'f'); // excludes [c..f]
        CharRange innerNeg = CharRange.isNotIn('d', 'e'); // excludes [d..e]
        CharRange disjointPos = CharRange.isIn('a', 'b'); // entirely outside excluded band

        assertTrue(outerNeg.contains(innerNeg));
        assertTrue(outerNeg.contains(disjointPos));
    }
}