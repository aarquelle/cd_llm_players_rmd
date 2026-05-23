import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange outerNeg = CharRange.isNotIn('c', 'e');   // excludes c..e
        CharRange innerPos = CharRange.isIn('a', 'b');      // entirely outside excluded block
        CharRange innerNeg = CharRange.isNotIn('b', 'f');   // excludes b..f (wider), should contain outerNeg

        assertAll(
                () -> assertTrue(outerNeg.contains(innerPos)),
                () -> assertFalse(outerNeg.contains(innerNeg))
        );
    }
}