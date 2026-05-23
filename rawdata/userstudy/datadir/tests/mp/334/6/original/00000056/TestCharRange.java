import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange negated = CharRange.isNotIn('c', 'f');      // everything except c-f
        CharRange otherNegated = CharRange.isNotIn('b', 'g'); // everything except b-g

        assertAll(
                () -> assertTrue(negated.contains(otherNegated), "Negated range should contain wider negated range (b-g)"),
                () -> assertFalse(negated.contains(CharRange.isIn('d', 'e')), "Negated range should not contain a range inside its excluded section (d-e)")
        );
    }
}