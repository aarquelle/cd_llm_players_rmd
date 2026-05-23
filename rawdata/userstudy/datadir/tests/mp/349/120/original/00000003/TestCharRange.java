import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        CharRange notNegated = CharRange.isIn('a', 'b');
        CharRange negated = CharRange.isNotIn('a', 'b');
        
        assertEquals(false, notNegated.isNegated());
        assertEquals(true, negated.isNegated());
    }
}