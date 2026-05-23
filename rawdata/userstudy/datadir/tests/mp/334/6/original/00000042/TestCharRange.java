import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange outerNegated = CharRange.isNotIn('d', 'f'); // everything except d-f
        assertAll(
                () -> assertTrue(outerNegated.contains(CharRange.isIn('a', 'c'))),
                () -> assertFalse(outerNegated.contains(CharRange.isIn('e', 'e')))
        );
    }
}