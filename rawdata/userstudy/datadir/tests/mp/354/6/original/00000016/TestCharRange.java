import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                // excludes fewer chars -> allows more chars
        CharRange negSmallExcluded = CharRange.isNotIn('d', 'f');
        // excludes more chars -> allows fewer chars
        CharRange negBigExcluded = CharRange.isNotIn('b', 'h');

        assertTrue(negSmallExcluded.contains(negBigExcluded));
        assertFalse(negBigExcluded.contains(negSmallExcluded));
    }
}