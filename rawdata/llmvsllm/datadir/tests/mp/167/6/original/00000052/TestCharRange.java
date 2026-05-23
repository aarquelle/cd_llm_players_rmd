import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange neg = CharRange.isNotIn('e', 'a');
        assertEquals("^a-e", neg.toString());
        assertEquals("TFTF",
                String.valueOf(neg.contains('z')) +
                String.valueOf(neg.contains('b')) +
                String.valueOf(neg.contains(CharRange.isIn('b', 'd'))) +
                String.valueOf(neg.contains(CharRange.isIn('a', 'e'))));
    }
}