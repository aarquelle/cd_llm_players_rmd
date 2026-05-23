import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange baseNeg = CharRange.isNotIn('m', 'p');
        CharRange inside = CharRange.isIn('n', 'o');
        CharRange outside = CharRange.isIn('a', 'b');

        assertAll(
                () -> assertFalse(baseNeg.contains(inside)),
                () -> assertTrue(baseNeg.contains(outside))
        );
    }
}