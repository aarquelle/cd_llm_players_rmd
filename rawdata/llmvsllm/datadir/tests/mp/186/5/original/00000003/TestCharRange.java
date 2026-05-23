import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange outerNegated = CharRange.isNotIn('b', 'd'); // everything except b-d
        CharRange inner = CharRange.isIn('c', 'c'); // inside excluded area
        assertFalse(outerNegated.contains(inner));
    }
}