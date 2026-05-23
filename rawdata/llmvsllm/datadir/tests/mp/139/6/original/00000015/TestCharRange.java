import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        CharRange range = CharRange.isIn('a', 'e');
CharRange other = CharRange.isIn('d', 'f');
assertFalse(range.contains(other));
assertFalse(other.contains(range));
    }
}