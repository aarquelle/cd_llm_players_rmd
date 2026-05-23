import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        CharRange cr = CharRange.isIn((char) (Character.MAX_VALUE - 5), Character.MAX_VALUE);
        assertTrue(cr.contains(CharRange.isIn((char) (Character.MAX_VALUE - 4), (char) (Character.MAX_VALUE - 1))));
    }
}