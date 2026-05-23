import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        CharRange out = CharRange.isIn('a', 'z');
        CharRange in = CharRange.isIn('b', 'c');
    
        assertTrue(out.contains(inner));
        assertFalse(in.contains(outer));
    }
}