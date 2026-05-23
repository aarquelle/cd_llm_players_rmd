import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange negOuter = CharRange.isNotIn('b', 'd'); // everything except b-d
        CharRange inner = CharRange.isIn('a', 'a');       // just 'a' (outside excluded zone)
        CharRange negInner = CharRange.isNotIn('c', 'c'); // everything except 'c'

        assertAll(
                () -> assertTrue(negOuter.contains(inner)),
                () -> assertFalse(negOuter.contains(negInner))
        );
    }
}