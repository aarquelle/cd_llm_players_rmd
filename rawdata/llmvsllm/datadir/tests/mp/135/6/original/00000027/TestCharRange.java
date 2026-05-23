import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        CharRange r1 = CharRange.isIn('e', 'a');
CharRange r2 = CharRange.isIn('a', 'e');
assertEquals(r2.hashCode(), r1.hashCode());
assertTrue(r1.equals(r2));
    }
}