import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        CharRange outerNeg = CharRange.isNotIn('a', 'z');
CharRange innerNeg = CharRange.isNotIn('b', 'y');
assertFalse(outerNeg.contains(innerNeg));
assertTrue(innerNeg.contains(outerNeg));
    }
}