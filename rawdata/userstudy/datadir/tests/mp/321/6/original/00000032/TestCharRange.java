import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange negated = CharRange.isNotIn('c', 'f'); // contains everything except [c..f]
        assertAll(
                () -> assertTrue(negated.contains(CharRange.isIn('a', 'b'))),
                () -> assertFalse(negated.contains(CharRange.isIn('b', 'c')))
        );
    }
}