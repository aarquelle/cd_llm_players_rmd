import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange outerNegated = CharRange.isNotIn('b', 'd'); // contains everything except b..d
        assertAll(
                () -> assertTrue(outerNegated.contains(CharRange.isIn('a', 'a'))),  // entirely outside excluded band
                () -> assertFalse(outerNegated.contains(CharRange.isIn('c', 'c')))  // inside excluded band
        );
    }
}