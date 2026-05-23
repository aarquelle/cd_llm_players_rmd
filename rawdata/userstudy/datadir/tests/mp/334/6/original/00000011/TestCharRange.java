import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange range = CharRange.is('a');
        try {
            range.contains((CharRange) null);
        } catch (IllegalArgumentException ex) {
            org.junit.Assert.assertEquals("The Range must not be null", ex.getMessage());
            return;
        }
        org.junit.Assert.fail("Expected IllegalArgumentException");
    }
}