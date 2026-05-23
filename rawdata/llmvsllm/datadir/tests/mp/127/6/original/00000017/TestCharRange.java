import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        CharRange thisNeg = CharRange.isNotIn('d', 'f');
CharRange otherNeg = CharRange.isNotIn('e', 'g');
assertFalse(thisNeg.contains(otherNeg));
assertTrue(otherNeg.contains('d'));
    }
}