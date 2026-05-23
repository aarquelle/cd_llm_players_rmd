import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        char start = 'a';
        char end = 'b';
        final char ch = 'c';
        CharRange range = CharRange.isNot(ch);
        assertEquals('c', range.getStart());
    }
}