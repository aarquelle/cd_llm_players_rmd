import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        int hc1 = CharRange.isIn('b', 'd').hashCode();
int hc2 = CharRange.isNotIn('b', 'd').hashCode();
assertNotEquals(hc1, hc2);
assertFalse(CharRange.isIn('b', 'd').equals(CharRange.isNotIn('b', 'd')));
    }
}