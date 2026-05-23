import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        CharRange c = CharRange.isIn('a','d');
        CharRange cc = CharRange.isNotIn('b','c');
        assertFalse(c.contains(cc));
       
    }
}