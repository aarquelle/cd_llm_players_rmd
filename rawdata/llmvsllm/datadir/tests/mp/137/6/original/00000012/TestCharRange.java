import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        CharRange outer = CharRange.isIn('a', 'e');
CharRange inner = CharRange.isIn('d', 'z');
assertFalse(outer.contains(inner));
assertTrue(inner.contains('z'));
    }
}