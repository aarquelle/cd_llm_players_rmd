import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange negated = CharRange.isNotIn('c', 'f'); // everything except c..f
        CharRange outside = CharRange.isIn('a', 'b');    // entirely outside excluded segment
        CharRange overlaps = CharRange.isIn('b', 'c');   // touches excluded boundary at 'c'

        assertAll(
                () -> assertTrue(negated.contains(outside)),
                () -> assertFalse(negated.contains(overlaps))
        );
    }
}