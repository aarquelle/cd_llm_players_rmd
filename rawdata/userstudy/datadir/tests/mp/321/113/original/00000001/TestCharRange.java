import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        CharRange range = new CharRange('a','b', false);
        CharRange range2 = new CharRange('a','a', false);
        
        assertTrue(range.contains(range2));
        
    }
}