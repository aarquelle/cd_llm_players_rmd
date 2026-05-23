import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange outerNeg = CharRange.isNotIn('d', 'f'); // everything except d-f
        CharRange innerPosInsideHole = CharRange.isIn('e', 'e');
        CharRange innerNegWithSmallerHole = CharRange.isNotIn('e', 'e'); // everything except e

        assertAll(
                () -> assertFalse(outerNeg.contains(innerPosInsideHole)),
                () -> assertFalse(outerNeg.contains(innerNegWithSmallerHole))
        );
    }
}