import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        CharRange range = CharRange.isNotIn('a', 'c');
String s1 = range.toString();
String s2 = range.toString();
assertSame(s1, s2);
assertEquals("^a-c", s1);
    }
}