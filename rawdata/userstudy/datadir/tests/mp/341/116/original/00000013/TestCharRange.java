import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        var range = CharRange.isIn('\x01', Character.MAX_VALUE);
        assertTrue(range.equals(CharRange.isNot('\0'));
    }
}