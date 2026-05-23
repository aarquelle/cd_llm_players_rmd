import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        CharRange ch = CharRange.isIn('b', 'y');
        CharRange ch2 = CharRange.isIn('b', 'z');
        CharRange chNot1 = CharRange.isNot('A');
        assertNotNull (chNot1);
    }
}