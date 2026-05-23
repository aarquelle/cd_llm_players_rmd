import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange negated = CharRange.isNotIn('f', 'b'); // should reorder to b-f and negate
        assertFalse(negated.contains(CharRange.isIn('d', 'g'))); // overlaps -> not fully contained

        CharRange notOne = CharRange.isNot('x');
        assertTrue(notOne.contains(CharRange.isNotIn('w', 'y'))); // negated contains negated iff inner covers outer
    }
}