import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange outerNeg = CharRange.isNotIn('c', 'f'); // everything except c-f
        assertAll(
                () -> assertTrue(outerNeg.contains(CharRange.isIn('a', 'b'))),   // entirely outside excluded block
                () -> assertFalse(outerNeg.contains(CharRange.isIn('b', 'c')))   // touches boundary -> not contained
        );
    }
}