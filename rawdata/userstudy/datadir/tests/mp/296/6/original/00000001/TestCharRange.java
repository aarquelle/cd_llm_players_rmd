import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange range = CharRange.is('a');
        assertFalse(range.isNegated());
        assertEquals('a', range.getStart());
        // Removed the third assertion to comply with the 2 assertion rule

    }
}