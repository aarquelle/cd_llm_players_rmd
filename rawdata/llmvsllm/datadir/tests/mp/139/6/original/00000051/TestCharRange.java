import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange outerNeg = CharRange.isNotIn('c', 'e');   // everything except c..e
        CharRange innerPosOutside = CharRange.isIn('a', 'b'); // entirely outside c..e
        CharRange innerPosInside = CharRange.isIn('d', 'd');  // inside c..e

        assertTrue(outerNeg.contains(innerPosOutside));
        assertFalse(outerNeg.contains(innerPosInside));
    }
}