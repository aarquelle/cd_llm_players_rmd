import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        CharRange outer = CharRange.isIn('a', 'z');
CharRange inner = CharRange.isIn('c', 'd');
assertTrue(outer.contains(inner));
assertFalse(inner.contains(outer));
    }
}