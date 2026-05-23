import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange outerNeg = CharRange.isNotIn('c', 'f'); // excludes c..f
        assertAll(
            () -> assertTrue(outerNeg.contains(CharRange.isNotIn('b', 'g'))),
            () -> assertFalse(outerNeg.contains(CharRange.isIn('d', 'e')))
        );
    }
}