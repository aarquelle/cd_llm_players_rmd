import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        CharRange range = CharRange.isIn('a', 'c');
CharRange neg = CharRange.isNotIn('a', 'c');
assertEquals("a-c", range.toString());
assertEquals("^a-c", neg.toString());
    }
}