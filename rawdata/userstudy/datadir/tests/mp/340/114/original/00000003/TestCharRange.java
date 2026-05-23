import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        char start = 'a';
        char end = 'b';
        final boolean negated = true;
        CharRange range = new CharRange(start, end, negated);
        assertEquals('a', range.getStart());
    }
}