import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        CharRange cr = CharRange.is(Character.MAX_VALUE);
        assertEquals(Character.MAX_VALUE, cr.getStart());
        assertEquals(Character.MAX_VALUE, cr.getEnd());
    }
}