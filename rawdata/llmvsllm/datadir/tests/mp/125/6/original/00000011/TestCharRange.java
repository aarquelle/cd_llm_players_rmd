import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        CharRange thisNeg = CharRange.isNotIn('c', 'f');
CharRange otherNegWiderExcluded = CharRange.isNotIn('b', 'g');
assertTrue(thisNeg.contains(otherNegWiderExcluded));
assertFalse(otherNegWiderExcluded.contains(thisNeg));
    }
}