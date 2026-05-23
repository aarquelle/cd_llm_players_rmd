import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        CharRange r1 = CharRange.is('a');
CharRange r2 = CharRange.isNot('a');
assertFalse(r1.equals(r2));
assertNotEquals(r1.hashCode(), r2.hashCode());
    }
}