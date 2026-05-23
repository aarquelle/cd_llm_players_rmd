import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        CharRange charRa = CharRange.isIn('a', 'z');
        assertFalse("Expected isNegated to be false", charRa.isNegated());
        charRa = CharRange.isNotIn('a', 'z');
        assertTrue("Expected isNegated to be true", charRa.isNegated());
    


    }
}