import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange negated = CharRange.isNotIn('d', 'f'); // everything except d-f
        CharRange insideExcluded = CharRange.isIn('e', 'e'); // within d-f
        CharRange outsideExcluded = CharRange.isIn('g', 'g'); // outside d-f

        assertAll(
            () -> assertFalse(negated.contains(insideExcluded)),
            () -> assertTrue(negated.contains(outsideExcluded))
        );
    }
}