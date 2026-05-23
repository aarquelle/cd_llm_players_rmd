import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange negatedOuter = CharRange.isNotIn('b', 'd'); // everything except b-d
        assertAll(
                () -> assertTrue(negatedOuter.contains(CharRange.isIn('a', 'a'))),   // outside excluded block
                () -> assertFalse(negatedOuter.contains(CharRange.isIn('c', 'c')))   // inside excluded block
        );
    }
}