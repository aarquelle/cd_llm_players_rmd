import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        CharRange neg1 = CharRange.isNotIn('d', 'f');
CharRange neg2 = CharRange.isNotIn('b', 'y');
assertFalse(neg2.contains(neg1));
assertTrue(neg1.contains(neg1));
    }
}