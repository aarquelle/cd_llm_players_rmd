import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        CharRange r1 = CharRange.isIn('a', 'c');
CharRange r2 = CharRange.isNotIn('a', 'c');
assertNotEquals(r1, r2);
assertNotEquals(r1.hashCode(), r2.hashCode());
    }
}