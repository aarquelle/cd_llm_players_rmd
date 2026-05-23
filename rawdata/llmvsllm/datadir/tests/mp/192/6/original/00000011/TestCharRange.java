import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        CharRange outer = CharRange.isIn('a', 'b');
CharRange other = CharRange.isIn('c', 'd');
assertFalse(outer.contains(other));
assertFalse(other.contains(outer));
    }
}