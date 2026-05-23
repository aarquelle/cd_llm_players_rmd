import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        // allows more chars
CharRange negSmallExcluded = CharRange.isNotIn('d', 'f');
// allows fewer chars
CharRange negBigExcluded = CharRange.isNotIn('b', 'h');
assertFalse(negSmallExcluded.contains(negBigExcluded));
assertTrue(negBigExcluded.contains(negSmallExcluded));
    }
}