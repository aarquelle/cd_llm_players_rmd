import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        CharRange isNot = CharRange.isNot('a');
        
        assertTrue(isNot.contains('b'));
        assertFalse(isNot.contains('a'));
    }
}