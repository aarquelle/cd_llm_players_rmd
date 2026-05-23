import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                Object r = CharRange.isNot('a').getClass().getMethod("isNegated").invoke(CharRange.isNot('a'));
        assertSame(Boolean.TRUE, r);
    }
}