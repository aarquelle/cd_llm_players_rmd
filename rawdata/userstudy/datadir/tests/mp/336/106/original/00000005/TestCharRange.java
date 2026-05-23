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

    @Test
    void testContainsNull() {
        CharRange base = range('a', 'z', false);

        assertThrows(IllegalArgumentException.class, () -> base.contains(null));
    }

    @Test
    void testContainsNegatedBothNegated() {
        // this: negiert
        CharRange base = range('d', 'm', true);
        // range: negiert
        CharRange r = range('f', 'h', true);

        // start >= r.start && end <= r.end
        assertTrue(base.contains(r));
    }

    @Test
    void testContainsNegatedVsNormal() {
        // this.negated = true
        CharRange base = range('d', 'm', true);
        // normaler range
        CharRange r = range('a', 'b', false);

        // r.end < start || r.start > end
        assertTrue(base.contains(r)); // 'a'..'b' liegt komplett außerhalb d..m
    }

    @Test
    void testContainsNormalVsNegatedShouldCoverAll() {
        // Normaler Bereich: \u0000 .. \uFFFF
        CharRange base = range((char) 0, Character.MAX_VALUE, false);
        CharRange r = range('a', 'z', true);

        assertTrue(base.contains(r));
    }

    @Test
    void testContainsNormalBothNormalContained() {
        CharRange base = range('a', 'z', false);
        CharRange r = range('c', 'f', false);

        assertTrue(base.contains(r));
    }

    @Test
    void testContainsNormalBothNormalNotContained() {
        CharRange base = range('a', 'f', false);
        CharRange r = range('k', 'z', false);

        assertFalse(base.contains(r));
    }

    }
}