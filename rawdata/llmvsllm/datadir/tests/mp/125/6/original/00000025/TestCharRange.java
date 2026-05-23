import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange negOuter = CharRange.isNotIn('m', 'p');
        assertFalse(negOuter.contains(CharRange.isNotIn('a', 'z')));

        CharRange negSingle = CharRange.isNot('x');
        assertFalse(negSingle.contains(CharRange.isIn('x', 'x')));
    }
}