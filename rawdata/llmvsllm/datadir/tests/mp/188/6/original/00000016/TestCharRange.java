import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        CharRange narrowNeg = CharRange.isNotIn('d', 'm');
CharRange wideNeg = CharRange.isNotIn('c', 'n');
assertTrue(narrowNeg.contains(wideNeg));
assertFalse(wideNeg.contains(narrowNeg));
    }
}