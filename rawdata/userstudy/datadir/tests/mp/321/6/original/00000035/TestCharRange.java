import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange negated = CharRange.isNotIn('c', 'e'); // everything except c-e
        CharRange outside = CharRange.isIn('a', 'b');    // fully outside excluded block
        CharRange inside = CharRange.isIn('d', 'd');     // inside excluded block

        assertAll(
                () -> assertTrue(negated.contains(outside)),
                () -> assertFalse(negated.contains(inside))
        );
    }
}