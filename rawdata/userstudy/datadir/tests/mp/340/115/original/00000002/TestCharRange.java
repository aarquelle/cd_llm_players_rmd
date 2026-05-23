import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
            CharRange ch = new CharRange('c', 'd', true);
        CharRange ch2 = new CharRange ('c', 'c', true);
        assertEquals(ch.is('c'),ch2);
    }
}