import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        CharRange c = CharRange.isNot((char) 255);
        
        assertFalse(c.contains((char) 255));
        assertTrue(c.contains((char) 230));
    }
}