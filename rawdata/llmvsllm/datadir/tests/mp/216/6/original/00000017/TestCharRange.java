import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        CharRange neg1 = CharRange.isNotIn('c', 'e');
CharRange neg2 = CharRange.isNotIn('d', 'g');
assertFalse(neg1.contains(neg2));
assertFalse(neg2.contains(neg1));
    }
}