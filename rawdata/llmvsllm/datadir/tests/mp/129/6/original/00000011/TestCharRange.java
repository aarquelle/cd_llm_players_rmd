import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        CharRange a = CharRange.isNotIn('c', 'd');
CharRange b = CharRange.isNotIn('b', 'e');
assertTrue(a.contains(b));
assertFalse(b.contains(a));
    }
}