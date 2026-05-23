import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange negatedOuter = CharRange.isNotIn('c', 'd'); // everything except c-d
        CharRange insideExcluded = CharRange.isIn('c', 'c');
        CharRange outsideExcluded = CharRange.isIn('a', 'b');

        assertAll(
                () -> assertFalse(negatedOuter.contains(insideExcluded)),
                () -> assertTrue(negatedOuter.contains(outsideExcluded))
        );
    }
}