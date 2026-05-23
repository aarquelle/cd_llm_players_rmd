import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange negatedOuter = CharRange.isNotIn('c', 'f'); // everything except c-f
        CharRange innerOutside = CharRange.isIn('a', 'b');    // entirely outside excluded block
        CharRange innerOverlaps = CharRange.isIn('b', 'd');   // overlaps excluded block

        assertTrue(negatedOuter.contains(innerOutside));
        assertFalse(negatedOuter.contains(innerOverlaps));
    }
}