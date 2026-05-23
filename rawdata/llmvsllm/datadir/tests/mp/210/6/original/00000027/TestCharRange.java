import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange negatedOuter = CharRange.isNotIn('c', 'f'); // everything except c-f
        assertTrue(negatedOuter.contains(CharRange.isIn('a', 'b')));
        assertFalse(negatedOuter.contains(CharRange.isIn('b', 'c')));
    }
}