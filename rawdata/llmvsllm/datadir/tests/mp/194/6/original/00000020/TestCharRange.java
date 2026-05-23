import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        CharRange a = CharRange.isIn('a', 'z');
CharRange b = CharRange.isNotIn('a', 'z');
assertFalse(a.equals(b));
assertFalse(b.equals(a));
    }
}