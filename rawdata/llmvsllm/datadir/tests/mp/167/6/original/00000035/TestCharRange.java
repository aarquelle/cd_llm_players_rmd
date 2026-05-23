import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange all = CharRange.isIn((char) 0, Character.MAX_VALUE);

        assertTrue(all.contains(CharRange.isNot('x')));
        assertFalse(CharRange.isIn('d', 'h').contains(CharRange.isNot('x')));
    }
}