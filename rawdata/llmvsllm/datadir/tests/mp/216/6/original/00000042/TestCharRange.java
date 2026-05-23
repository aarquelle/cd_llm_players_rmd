import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange outer = CharRange.isNotIn('d', 'f'); // everything except d-f
        assertAll(
                () -> assertTrue(outer.contains(CharRange.isNotIn('c', 'g'))),  // outer excluded set is subset of inner excluded set
                () -> assertFalse(outer.contains(CharRange.isNotIn('e', 'h')))  // outer excluded set not subset of inner excluded set
        );
    }
}