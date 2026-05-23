import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        CharRange r = CharRange.isNotIn('a', 'e');
assertTrue(r.contains('f'));
assertFalse(r.contains('c'));
    }
}