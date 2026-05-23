import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        CharRange outer = CharRange.isIn('a', 'c');
CharRange other = CharRange.isIn('b', 'd');
assertFalse(outer.contains(other));
assertFalse(other.contains(outer));
    }
}