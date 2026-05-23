import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        // test here!
        private CharRange range(char start, char end, boolean negated) {
            return new CharRange(start, end, negated);
        }
        
        CharRange base = range('a', 'z', false);
        assertThrows(IllegalArgumentException.class, () -> base.contains(null));
    }
}