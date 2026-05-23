import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        CharRange a = CharRange.isNotIn('d', 'f');
CharRange b = CharRange.isNotIn('c', 'g');
assertTrue(a.contains(b));
assertFalse(b.contains(a));
    }
}