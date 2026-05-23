import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        CharRange cr = CharRange.isIn('a','c');
        CharRange cr2 = CharRange.isIn('b', 'c');
        assertTrue(cr.contains(cr2));
    }
}